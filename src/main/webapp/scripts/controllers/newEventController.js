
angular.module('demojs').controller('NewEventController', function ($scope, $location, locationParser, EventResource , UserResource) {
    $scope.disabled = false;
    $scope.$location = $location;
    $scope.event = $scope.event || {};
    
    $scope.formatList = [
        "BBL",
        "SOIREE"
    ];
    
    $scope.speakersList = UserResource.queryAll(function(items){
        $scope.speakersSelectionList = $.map(items, function(item) {
            return ( {
                value : item.id,
                text : item.id
            });
        });
    });
    $scope.$watch("speakersSelection", function(selection) {
        if (typeof selection != 'undefined') {
            $scope.event.speakers = [];
            $.each(selection, function(idx,selectedItem) {
                var collectionItem = {};
                collectionItem.id = selectedItem.value;
                $scope.event.speakers.push(collectionItem);
            });
        }
    });


    $scope.save = function() {
        var successCallback = function(data,responseHeaders){
            var id = locationParser(responseHeaders);
            $location.path('/Events/edit/' + id);
            $scope.displayError = false;
        };
        var errorCallback = function() {
            $scope.displayError = true;
        };
        EventResource.save($scope.event, successCallback, errorCallback);
    };
    
    $scope.cancel = function() {
        $location.path("/Events");
    };
});