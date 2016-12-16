var maps = function() {

	/*
	 * PRIVADO
	 */
    
    var _DEFAULT_MAP_CENTER_LAT = -4.9625775161058945;
    var _DEFAULT_MAP_CENTER_LNG = -39.02196186826172;
    var _DEFAULT_MAP_CENTER_ZOOM = 15;
    var _map;
    var _isMapLoaded = false;

    var _markers = {};	
    
    var _lastClickedMarker = null;
    
    var _onMarkerSelectedListener = function(m) {};
    var _onMarkerUnselectedListener = function(m) {};
    var _onInfoClosedListener = function(m) {};
    
    var _onBoundsChangedListener = function(b) {}
    var _onZoomChangedListener = function(b) {}
    var _onMapClicked = function(pos) {};
    
    var _clusterizeMarkers = function() {}
    
    var _setMarker = function(setts) {
        
        var marker = _getMarkerById(setts.id, setts.dataType);
        if(marker) {
            marker.setOptions(setts);
        } else {
            marker = _createMarker(setts);
        }
        
        return marker;
    }
    
    var _createMarker = function(setts) {
        var marker = new google.maps.Marker(setts);
        marker._polyLines = [];
        marker.setMap(_map);
        
        if(!_markers[setts.dataType])
            _markers[setts.dataType] = []
        _markers[setts.dataType].push(marker);
        
        google.maps.event.addListener(marker, 'click', function() {
            _onMarkerClicked(marker);
        }); 
        
        return marker;
    }
    
    var _getMarkerById = function(id, dataType) {
        for(k in _markers[dataType])
            if(_markers[dataType][k].id === id)
                return _markers[dataType][k];
        return null;
    }
    
    var _removeMarker = function(marker) {
        if(marker && _markers[marker.dataType]) {
            for(var i = 0; i < _markers[marker.dataType].length; i++)
                if(_markers[marker.dataType][i].id === marker.id) {
                    _markers[marker.dataType].splice(i, 1);
                    break;
                }

            _removePolyLine(marker);
            _hideMarkerInfo(marker);
            marker.setMap(null);

            if(_lastClickedMarker && _lastClickedMarker.id == marker.id && _lastClickedMarker.dataType == marker.dataType) {
                _unselectMarker();
            }
        }
                
    }
    
    var _removeByType = function(dataType) {
        for(; _markers[dataType].length > 0;)
            _removeMarker(_markers[dataType][0])
    }
    
    var _onMarkerClicked = function(marker) {
        if(marker && _lastClickedMarker && _lastClickedMarker.id === marker.id && _lastClickedMarker.dataType == marker.dataType) 
            _unselectMarker()
        else
            _selectMarker(marker);
    }

    var _selectMarker = function(marker) {
        _unselectMarker();
        _lastClickedMarker = marker;
        
        marker.setAnimation(google.maps.Animation.BOUNCE);
        
        _onMarkerSelectedListener(marker);
    }
    
    var _unselectMarker = function() {
        if(_lastClickedMarker) {
            var tmp = _lastClickedMarker;
            _lastClickedMarker = null;
            tmp.setAnimation(null);
            _onMarkerUnselectedListener(tmp);
        }
    }
    
    var _showMarkerInfo = function(marker, content) {
        if(marker._infoWindow) {
            marker._infoWindow.setContent(content);
        } else {
            marker._infoWindow = new google.maps.InfoWindow({
                content: content
            });
            google.maps.event.addListener(marker._infoWindow, 'closeclick', function(){
                _onInfoClosedListener(marker);
            });
        }
        marker._infoWindow.open(_map, marker);
    }
    
    var _hideMarkerInfo = function(marker) {
        if(marker && marker._infoWindow)
            marker._infoWindow.close();
    }
    
    var _hideAllMarkerInfos = function() {
        for(k in _markers)
            _hideMarkerInfo(_markers[k]);
    }
    
    var _setMarkerPolyLine = function(marker, setts) {
        marker = _getMarkerById(marker.id, marker.dataType);
        
        if(marker) {
            var polyLine = _getPolyLineById(marker, setts.id);

            if(polyLine) {
                
                polyLine.setOptions(setts);
            } else {
                
                _createPolyLine(marker, setts);
            }
        }
    }
    
    var _createPolyLine = function(marker, setts) {
        if(!setts.geodesic) setts["geodesic"] = true;
        if(!setts.strokeOpacity) setts["strokeOpacity"] = 1.0;
        if(!setts.strokeWeight) setts["strokeWeight"] = 2;
        var line = new google.maps.Polyline(setts);
        line.setMap(_map);
        marker._polyLines.push(line);
    }
    
    var _getPolyLineById = function(marker, id) {
        for(k in marker._polyLines)
            if(marker._polyLines[k].id === id)
                return marker._polyLines[k];
        
        return null;
    }
    
    var _removePolyLine = function(marker, id) {

        if(marker && marker._polyLines) {
            if(id) {
                for(k in marker._polyLines) {
                    if(marker._polyLines[k].id === id) {
                        marker._polyLines[k].setMap(null);
                        marker._polyLines.splice(k, 1);
                        break;
                    }
                }
            } else {
                for(k in marker._polyLines) {
                    marker._polyLines[k].setMap(null);
                }
                marker._polyLines = [];
            }
        }
        
    }
    
    var _saveMapState = function() {
        Cookies.set("maps_map_zoom", _map.getZoom());
        Cookies.set("maps_map_lat", _map.getCenter().lat());
        Cookies.set("maps_map_lng", _map.getCenter().lng());
    }
    
    var _getSavedMapState = function() {
        return {
            zoom: parseFloat(Cookies.get("maps_map_zoom")) || _DEFAULT_MAP_CENTER_ZOOM, 
            lat: parseFloat(Cookies.get("maps_map_lat")) || _DEFAULT_MAP_CENTER_LAT, 
            lng: parseFloat(Cookies.get("maps_map_lng")) || _DEFAULT_MAP_CENTER_LNG
        };
    }

	/*
	 * PÚBLICO
	 */

	return {

		doInit : function(mapElement, onFinish, onFailed) {

			try {
				
				google;
				
			} catch (e) {
				
				console.error("Erro ao carregar mapa!");
				if(onFailed) onFailed();				
				return;
				
			}
            
            // Loading from cookies
            var savedMap = _getSavedMapState();            

            _map = new google.maps.Map($(mapElement)[0], {

                center: {lat: savedMap.lat, lng: savedMap.lng},
                zoom: savedMap.zoom, 
                mapTypeControl: true,
                mapTypeControlOptions: {
                    style: google.maps.MapTypeControlStyle.DROPDOWN_MENU,
                    position: google.maps.ControlPosition.LEFT_TOP
                },
                zoomControl: true,
                zoomControlOptions: {
                    position: google.maps.ControlPosition.LEFT_CENTER
                },
                scaleControl: true,
                streetViewControl: false,
                fullscreenControl: false

            });
            
            _map.addListener('idle', function(){
                if(!_isMapLoaded && onFinish)
                    onFinish();
                _isMapLoaded = true;
            });
            
            _map.addListener('bounds_changed', function() {
				_onBoundsChangedListener(_map.getBounds());
                _saveMapState();
			});
            
            _map.addListener('zoom_changed', function() {
				_onZoomChangedListener(_map.getZoom());
			});
            
            _map.addListener('click', function(e) {
	            _onMapClicked(e.latLng);
	        });
            
            _clusterizeMarkers = function(){
                log("Culsterizing: "+ _markers.length);
                maps_marker_clustering.doCluster(_markers, _map, $(mapElement).height(), _map.getZoom());
                log("Culsterize finished!");
            }
            
            
        }, 
        
        doInitMapSearchBox : function(searchInputElement) {
			
			_map.addListener('click', function() {
				$(searchInputElement).focusout();
			})

			// Create the search box and link it to the UI element.
			var input = $(searchInputElement)[0];
			var searchBox = new google.maps.places.SearchBox(input);
			//map.controls[google.maps.ControlPosition.TOP_LEFT].push(input);

			// Bias the SearchBox results towards current map's viewport.
			_map.addListener('bounds_changed', function() {
				searchBox.setBounds(_map.getBounds());
			});

			var markers = [];
			// Listen for the event fired when the user selects a prediction and retrieve
			// more details for that place.
			searchBox.addListener('places_changed', function() {
				var places = searchBox.getPlaces();

				if (places.length == 0) {
					return;
				}

				// Clear out the old markers.
				markers.forEach(function(marker) {
				marker.setMap(null);
				});
				markers = [];

				// For each place, get the icon, name and location.
				var bounds = new google.maps.LatLngBounds();
				places.forEach(function(place) {
					var icon = {
						url: place.icon,
						size: new google.maps.Size(71, 71),
						origin: new google.maps.Point(0, 0),
						anchor: new google.maps.Point(17, 34),
						scaledSize: new google.maps.Size(25, 25)
					};

					// Create a marker for each place.
					markers.push(new google.maps.Marker({
						map: _map,
						icon: icon,
						title: place.name,
						position: place.geometry.location
					}));

					if (place.geometry.viewport) {
						// Only geocodes have viewport.
						bounds.union(place.geometry.viewport);
					} else {
						bounds.extend(place.geometry.location);
					}
				});

				_map.fitBounds(bounds);

			});

		}, 
        
        getMap : function() {
            return _map;
        }, 
        
        getMarker : function(id, dataType) {
            return _getMarkerById(id, dataType);
        },
        
        getSelectedMarker : function() {
            return _lastClickedMarker;
        },
        
        getMarkers : function() {
            return _markers;
        },
        
        doSetMarker : function(setts) {            
            return _setMarker(setts);
        },
        
        doRemoveMarker : function(marker) {            
            _removeMarker(marker);
        },
        
        doRemoveMarkerByType : function(dataType) {            
            _removeByType(dataType);
        },
        
        doSelectMarker : function(marker) {            
            _selectMarker(marker);
        },
        
        doUnselectMarker : function() {            
            _unselectMarker();
        },
        
        doSetPolyLine : function(marker, setts) {            
            _setMarkerPolyLine(marker, setts);
        },
        
        doRemovePolyLine : function(marker, id) {
            _removePolyLine(marker, id);
        },
        
        doShowMarkerInfo : function(marker, content) {
            _showMarkerInfo(marker, content);
        }, 
        
        doHideMarkerInfo : function(marker) {
            _hideMarkerInfo(marker);
        }, 
        
        doSetOnMarkerSelectListener : function(listener) {
            _onMarkerSelectedListener = listener;
        }, 
        
        doSetOnMarkerUnselectListener : function(listener) {
            _onMarkerUnselectedListener = listener;
        }, 
        
        doSetOnInfoWindowCloseListener : function(listener) {
            _onInfoClosedListener = listener;
        }, 
        
        doSetOnMapBoundsChangeListener : function(listener) {
            _onBoundsChangedListener = listener;
        }, 
        
        doSetOnMapZoomChangeListener : function(listener) {
            _onZoomChangedListener = listener;
        },
        
        doSetOnMapClickedListener : function(listener) {
            _onMapClicked = listener;
        }, 
        
        doClusterizeMarkers : function() {
            _clusterizeMarkers();
        }
        
	};

} ();