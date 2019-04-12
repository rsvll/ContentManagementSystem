console.log("AT LEAST IT IS RUNNING???");
$(document).ready(function () {
    loadCatsAndTags();
    
    $('#create-blog').click(function () {
        addTagsToUser();
        console.log("AT LEAST IT IS RUNNING???");
    });









});

function loadCatsAndTags() {
    var catDiv = $('#catRest');
    var tagDiv = $('#tagRest');
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/BlogCMS/obtainAllCategories',
        success: function (data) {
            data.forEach(e => {
                catDiv.append(` <label>
                <input type="radio" class="" name="cats"  " value="${e.catId}"> ${e.catName}
                </label><br>`);
            })
        },
        error: function () {
            console.log("Problems found")
        }
    });
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/BlogCMS/obtainAllTags',
        success: function (data) {
            data.forEach(e => {
                tagDiv.append(` <label>
                <input type="checkbox" name = "tags" value="${e.tagId}"> ${e.tagName}
                </label><br>`);
            })
        },
        error: function () {
            console.log("Problems found")
        }
    });
    
}

 function addTagsToUser(){
     var username = $('#username').val();
     var tagIds = $('#tags').val();
     
}
