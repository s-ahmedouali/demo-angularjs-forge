

angular.module('demojs').controller('EditEventController', function($scope, $routeParams, $location, EventResource , UserResource) {
    var self = this;
    $scope.disabled = false;
    $scope.$location = $location;
    
    $scope.get = function() {
        var successCallback = function(data){
            self.original = data;
            $scope.event = new EventResource(self.original);
            UserResource.queryAll(function(items) {
                $scope.speakersSelectionList = $.map(items, function(item) {
                    var wrappedObject = {
                        id : item.id
                    };
                    var labelObject = {
                        value : item.id,
                        text : item.id
                    };
                    if($scope.event.speakers){
                        $.each($scope.event.speakers, function(idx, element) {
                            if(item.id == element.id) {
                                $scope.speakersSelection.push(labelObject);
                                $scope.event.speakers.push(wrappedObject);
                            }
                        });
                        self.original.speakers = $scope.event.speakers;
                    }
                    return labelObject;
                });
            });
        };
        var errorCallback = function() {
            $location.path("/Events");
        };
        EventResource.get({EventId:$routeParams.EventId}, successCallback, errorCallback);
    };

    $scope.isClean = function() {
        return angular.equals(self.original, $scope.event);
    };

    $scope.save = function() {
        var successCallback = function(){
            $scope.get();
            $scope.displayError = false;
        };
        var errorCallback = function() {
            $scope.displayError=true;
        };
        $scope.event.$update(successCallback, errorCallback);
    };

    $scope.cancel = function() {
        $location.path("/Events");
    };

    $scope.remove = function() {
        var successCallback = function() {
            $location.path("/Events");
            $scope.displayError = false;
        };
        var errorCallback = function() {
            $scope.displayError=true;
        }; 
        $scope.event.$remove(successCallback, errorCallback);
    };
    
    $scope.formatList = [
        "BBL",  
        "SOIREE"  
    ];
    $scope.speakersSelection = $scope.speakersSelection || [];
    $scope.$watch("speakersSelection", function(selection) {
        if (typeof selection != 'undefined' && $scope.event) {
            $scope.event.speakers = [];
            $.each(selection, function(idx,selectedItem) {
                var collectionItem = {};
                collectionItem.id = selectedItem.value;
                $scope.event.speakers.push(collectionItem);
            });
        }
    });
    
    $scope.get();
});