<h1> CECS 453 Final Project </h1>

<h2> Caution </h2>
<p> Do not try to sign up with your real account details if you want to try this out. The app can still work if you used fake credentials. This is mainly created for learning purposes.</p>

<!--Collaborators-->
<h2> Collaborators </h2>
<dl>
    <dd> Khai Trinh - Programmer & Designer </li>
    <dd> Nikko Chan - Programmer & Designer </li>
</dl>


<!--Description-->
<h2> Description </h2>
<p> For our final project, we were tasked to come up with a project idea. We wanted to make an app that utilized most of what we learned in our summer class with an additional of wanting to implement Firebase. </p>
<p> Our app's function is to be able to allow the user to upload images to our database, and allow said user to view all of their uploaded images anytime they want as long as they can connect to our database. </p>

<!--Technologies Used-->
<h2> Technologies Used </h2>
<ul>
  <li> Android Studio (IDE)</li>
  <li> AwesomeValidation (TPL) </li>
  <li> Draw.io (Design/Planning) </li>
  <li> Firebase (Database) </li>
  <li> Picasso  (TPL) </li>
</ul>

<!--Design-->
<h2> Project Design </h2> 
<img src="https://github.com/KhaiTrinh/CECS453FinalProject/blob/main/app/pictures%20for%20read%20me/453%20Final%20Project.png" height="550"/>

<!--Object Model-->
<h2> Object Model </h2>
<img src="https://github.com/KhaiTrinh/CECS453FinalProject/blob/main/app/pictures%20for%20read%20me/Object%20Model.png" height="550"/>

<!--Functionalities-->
<h2> Functionalities </h2>

<!--Login-->
<h3> Login </h3>
<p> We used Firebase's Authentication to validate the login </p>
<img src="https://github.com/KhaiTrinh/CECS453FinalProject/blob/main/app/pictures%20for%20read%20me/register.jpg" height="550"/>

<!--Register-->
<h3> Register </h3>
<p> Awesome Validation API was used to validate the input from the user. Making sure that the user's email follows the format of "@email.com", has a complex password (i.e. one uppercase, one lowercase, one special character, and one number), and checks to see if the user verified said password. </p>
<img src="https://github.com/KhaiTrinh/CECS453FinalProject/blob/main/app/pictures%20for%20read%20me/login.jpg" height="550"/>

<!--Upload-->
<h3> Upload </h3>
<p> Browsing for an image calls an implicit intent for the user to choose the app that they want to use to pick an image. The image will be loaded as a preview, and the user may click on the upload button to be sent to our Firebase storage. </p>
<img src="https://github.com/KhaiTrinh/CECS453FinalProject/blob/main/app/pictures%20for%20read%20me/upload.jpg" height="550"/>

<!--Vault-->
<h3> Vault </h3>
<p> The vault activity is where the user will be able to view all of the images that they've stored with us. </p> 
<img src="https://github.com/KhaiTrinh/CECS453FinalProject/blob/main/app/pictures%20for%20read%20me/vault.jpg" height="550"/>


<!--Delete Image-->
<h3> Delete Image </h3>
<p> If the user wants to delete an image from their vault, all they need to do is press down on the image and a drop down menu will appear allowing the user to delete said image. </p>
<img src="https://github.com/KhaiTrinh/CECS453FinalProject/blob/main/app/pictures%20for%20read%20me/delete%20image.jpg" height="550"/>

<!--What we Learned-->
<h2> What we learned </h2>

<!--Teamwork-->
<h3> Teamwork makes the Dreamwork </h3>
<p> Since we only had a week to make this to plan and design, teamwork was definitely one of the key factors of getting it done in time My partner and I would always keep each other updated with the updates that we've down with the code. We would also get on call to explain the code that we added, so that we would both be on the same page. </p>

<!--Downloading Images to Load on ImageViews-->
<h3> Loading Images through URL </h3>
<p> We used another third party library called Picasso to handle the images</p>

<!--Firebase-->
<h3> Firebase </h3>

<!--What we think needs improvement-->
<h2> What we think needs improvement </h2>
<ul>
  <li> </li>
</ul>






