                             <link rel="stylesheet" href="assets/css/accountsetting.css">               
                                            <div class="form-group">
                                                <p class="font-weight-semibold">Username: ${user.username} <span><a href="#" onclick="togglePopup()" style="float:right">Change</a></span> </p>
                                            </div>
                                            
                                            <div class="popup" id="popup-1">
                                                <div class="overlay" onclick="togglePopup()"></div>
                                                <div class="content">
                                                    <div class="close-btn" onclick="togglePopup()">&times;</div>
                                                    <h1>Change username</h1>
                                                    <p>Old username: ${user.username}</p>
                                               
                                                        <input type="text" name="newUsername" id="newUsername" placeholder="new username">
                                                        <span class="red-text accent-4" id="result" class="result"></span>
                                                        <hr>
                                                        <input type="submit" value="Done">
                                                 
                                                          <script>
                                                            $(document).ready(function(){
                                                                $('#newUsername').change(function(){
                                                                    var newUsername = $('#newUsername').val();
                                                                    $.ajax({
                                                                        type:'POST',
                                                                        data:{newUsername:newUsername},
                                                                        url:'ChangeUsername',
                                                                        success: function(result){
                                                                            $('#result').html(result);
                                                                        }
                                                                    });
                                                                });
                                                            });
                                                        </script>
                                                </div>
                                                </div>
                                                      <script>
             function togglePopup() {
                document.getElementById("popup-1").classList.toggle("active");
            }
        </script>
             