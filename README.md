<h1> CECS 453 Final Project </h1>

<h2> Caution </h2>
<p> Do not use your real credentials when creating a account. You can use fake credentials as we didn't implement a verification for users. This is mainly for learning purposes.</p>

<!--Collaborators-->
<h2> Collaborators </h2>
<dl>
    <dd> Khai Trinh - Programmer & Designer </li>
    <dd> Nikko Chan - Programmer & Designer </li>
</dl>


<!--Description-->
<h2> Description </h2>
<p> For our final project, we were tasked to come up with our own application. We wanted to make an app that utilized most of what we learned in our summer class with the addition of wanting to implement Firebase. </p>
<p> Image Vault is used to store images to the cloud. The user is able to view all of the images that they've uploaded as long as they are connected to a wi-fi service. They also have the option to delete an image. </p>

<!--Technologies Used-->
<h2> Technologies Used </h2>
<ul>
  <li> Android Studio (IDE)</li>
  <li> AwesomeValidation (Third Party Library) </li>
  <li> Draw.io (Design/Planning) </li>
  <li> Firebase (Database) </li>
  <li> Picasso  (Third Party Library) </li>
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
<p> Since we only had a week and a half to make this, teamwork was definitely one of the key factors of getting it done in time. My partner and I would always keep each other updated with the updates that we've done. We would also get on call to explain the code that we added, so that we would both be on the same page. </p>

<!--Downloading Images to Load on ImageViews-->
<h3> Loading Images through URL </h3>
<p> We used another third party library called Picasso to handle the loading of images. In this project we used a recycler view to display each image that the user uploads. We use the Picasso library to download the image from the url given, and place it in our onBindViewHolder() in which binds the view holder with the image. </p>

<!--Firebase-->
<h3> Firebase </h3>
<p> My partner and I really enjoyed how user-friendly Firebase was. It took us a few days to understand what we needed to do with firebase in respect to our app's objective, but still was an enjoyable experience</p>

<!--Storage-->
<h4> Firebase Storage </h4>
<p> We chose to store the images in the storage as opposed to the realtime database because we heard that it would be better, especially for larger sized images. </p>
<p> Storing an image requries the StorageTask class. Note that each user will have their own directory in our storage. To make sure that the user is uploading in their directory, we had to use the StorageReference class to hold the value of FirebaseStorage.getInstance().child(path). This will get a reference to the path string where path is the user's directory path in our storage.</p>

<!--Realtime Database-->
<h4> Firebase Realtime Database </h4>
<p> The image urls from the storage will be stored in here. Also, the unique value will be a randomly generated id that represents the user's unique id. This is to isolate the user's image urls from the other.</p>
<p> Just like the Storage, we also needed a DatabaseReference class to hold the value of FirebaseDatabase.getInstance().child(path). The path being the directory of the user's account. We created a helper class called Upload that will store the Url along with a caption (for the image). A helper class was used to properly store values into our database called Upload. </p>

<!--Authentication-->
<h4> Firebase Authentication </h4>

<!--What we think needs improvement-->
<h2> What we think needs improvement </h2>
<ul>
  <li>We were thinking of instead to utilize a grid layout that displays all of the images, and that by clicking an image would enlarge it for a better view </li>
  <li> We wanted to implement a sorting method that would sort the images in terms of recent to oldest </li>
  <li> Implement machine learning to define objects within an image</li>
<li> Implement a way to verfiy the user like email verification. </li>  
</ul>






