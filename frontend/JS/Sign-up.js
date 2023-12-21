// Account log in info
let usernameInput = document.getElementById("usernameInput");
let usernameAvailability = document.getElementById("usernameAvailability");
let passwordInput = document.getElementById("passwordInput");

const profilePicInput = document.getElementById("profilePicInput");
const profilePicPreview = document.getElementById("profilePicPreview");

// Personal data
let firstNameInput = document.getElementById("firstNameInput");
let lastNameInput = document.getElementById("lastNameInput");
let emailInput = document.getElementById("emailInput");
let phoneNumberInput = document.getElementById("phoneNumberInput");
let phoneNumberValidity = document.getElementById("phonenumberValidity");
let birthDateInput = document.getElementById("birthDateInput");
let birthDateValidity = document.getElementById("birthdayValidation");
let addressInput = document.getElementById("addressInput");

// Guardian's data
let guardianFirstNameInput = document.getElementById("guardianFirstNameInput");
let guardianLastNameInput = document.getElementById("guardianLastNameInput");
let guardianCareerInput = document.getElementById("guardianCareerInput");
let guardianPhoneNumberInput = document.getElementById("guardianPhoneNumberInput");
let guardianPhonenumberValidity = document.getElementById("guardianPhonenumberValidity");

// adding the default pic 
profilePicPreview.src ="assets/default-icon2.jpeg";
profilePicPreview.style.display = "block";

let clearInputValues = (container) => {
    const inputElements = container.querySelectorAll("input");
    inputElements.forEach((input) => {
        input.value = "";
    });
    profilePicPreview.src ="assets/default-icon2.jpeg";
    usernameAvailability.style.display="none";
    birthDateValidity.style.display="none";
    phoneNumberValidity.style.display="none";
    guardianPhonenumberValidity.style.display="none";

}

//---------------- alert -------------------//

let alertDisplay = document.getElementById("alert")
    // alert close btn event
document.getElementById("btn-alert-close").addEventListener("click",()=>{
    alertDisplay.style.display='none'
})
    // show alert function
let showAlert = (color,context) => {
    document.getElementById("alertContext").textContent = context;
    alertDisplay.style.display='block'
    alertDisplay.style.backgroundColor = color; 

    setTimeout(() => {
        alertDisplay.style.display = 'none';
    }, 7000);
}


//---------------- profile pic preview  -------------------//

profilePicInput.addEventListener("change",  () => {
  const file = profilePicInput.files[0];
  if (file) {
    const reader = new FileReader();

    reader.onload = function (e) {
      profilePicPreview.src = e.target.result;
      profilePicPreview.style.display = "block";
    };

    reader.readAsDataURL(file);
  } else {
    reader.readAsDataURL(file);
    profilePicPreview.style.display = "block";
  }
});

//--------- Event listener for checking duplicate username ---------//

usernameInput.addEventListener("input", async () => {
    try {
        const response = await fetch("http://localhost:8080/Student/CheckDuplicateUsername?username=" + usernameInput.value);
        const isDuplicate = await response.json();

        if (isDuplicate) {
            usernameAvailability.style.display="block";
            usernameAvailability.textContent = "Username is already taken.";
            usernameAvailability.style.color = "red";
        } else {
            usernameAvailability.style.display="block";
            usernameAvailability.textContent = "Username is available!";
            usernameAvailability.style.color = "green";
        }
    } catch (error) {
        console.error("Error checking username ", error);
    }
});

//--------- Event listener for checking phone number validity  ---------//

phoneNumberInput.addEventListener("input", async () => {
    try {
        const response = await fetch("http://localhost:8080/Student/CheckPhoneNumber?phoneNumber=" + phoneNumberInput.value);
        const isValid = await response.json();

        if (isValid) {
            phoneNumberValidity.style.display="block";
            phoneNumberValidity.textContent = 'Valid Phone Number';
            phoneNumberValidity.style.color = 'green';
        } else {
            phoneNumberValidity.style.display="block";
            phoneNumberValidity.textContent = 'Invalid Phone Number';
            phoneNumberValidity.style.color = 'red';
        }
    } catch (error) {
        console.error("Error checking phone number ", error);
    }
});

//--------- Event listener for checking Birthday validity ---------//

birthDateInput.addEventListener("input", async () => {
    try {
        const response = await fetch("http://localhost:8080/Student/CheckBirthdayValidation?birthday=" + birthDateInput.value);
        const isValid = await response.json();

        if (isValid) {
            birthDateValidity.style.display="block";
            birthDateValidity.textContent = 'Valid  Birthday';
            birthDateValidity.style.color = 'green';
        } else {
            birthDateValidity.style.display="block";
            birthDateValidity.textContent = 'Invalid Birthday';
            birthDateValidity.style.color = 'red';
        }
    } catch (error) {
        console.error("Error checking Birthday ", error);
    }
});

//--------- Event listener for checking Guardian's phone number validity  ---------//

guardianPhoneNumberInput.addEventListener("input", async () => {
    try {
        const response = await fetch("http://localhost:8080/Student/CheckPhoneNumber?phoneNumber=" + guardianPhoneNumberInput.value);
        const isValid = await response.json();

        if (isValid) {
            guardianPhonenumberValidity.style.display="block";
            guardianPhonenumberValidity.textContent = 'Valid Phone Number';
            guardianPhonenumberValidity.style.color = 'green';
        } else {
            guardianPhonenumberValidity.style.display="block";
            guardianPhonenumberValidity.textContent = 'Invalid Phone Number';
            guardianPhonenumberValidity.style.color = 'red';
        }
    } catch (error) {
        console.error("Error checking phone number ", error);
    }
});

//-----------------  Function to create a new user ------------------//

form.addEventListener("submit",  async (event) => {

   // Check if the usernameAvailability indicator is green
    const isUsernameAvailable = usernameAvailability.style.color === 'green';
    
   // Check if the phoneNumberValidity indicator is green
    const isPhoneNumberValid = phoneNumberValidity.style.color === 'green';

    //Check if the BithdayValidity indicator is green
    const isBirthdayValid = birthDateValidity.style.color === 'green';

   // Check if the phoneNumberValidity indicator is green
    const isGuardianPNValid = guardianPhonenumberValidity.style.color === 'green';
    
    // If all conditions are true, create the user
    if(!isUsernameAvailable){
        event.preventDefault();
        showAlert("red"," ! Check your username again")
    }else if(!isPhoneNumberValid){
        event.preventDefault();
        showAlert("red"," ! Check your Phone Number again")
    }else if(!isBirthdayValid){
        event.preventDefault();
        showAlert("red"," ! Check your Birthday again")
    }else if(!isGuardianPNValid){
        event.preventDefault();
        showAlert("red"," ! Check Guardian's Phone Number again")
    }else{
        fetch("http://localhost:8080/Student/SignUp", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                "firstName": firstNameInput.value,
                "lastName": lastNameInput.value,
                "userName": usernameInput.value,
                "email": emailInput.value,
                "phoneNumber": phoneNumberInput.value,
                "address":addressInput.value,
                "password": passwordInput.value,
                "birthday": birthDateInput.value,
                "guardian": {
                    "firstName": guardianFirstNameInput.value,
                    "lastName": guardianLastNameInput.value,
                    "phoneNumber": guardianPhoneNumberInput.value,
                    "career": guardianCareerInput.value 
                }
            })
        })
        .then(response=>response.json())
        .then(response => {
            const selectedFile = profilePicInput.files[0];
            const formData = new FormData();
            formData.append('image', selectedFile);
            return fetch(`http://localhost:8080/Student/SetProfilePic?id=${response.studentId}`, {
                method: "POST",
                body: formData
            })
            .then(uploadResponse => {
                if (uploadResponse.ok) {
                    clearInputValues(form);
                    showAlert("green", "Registration complete.");
                } else {
                    clearInputValues(form);
                    showAlert("orange", "No profile picture sent. Using default profile picture.");
                }
            })
            .catch(error => {
                clearInputValues(form);
                showAlert("red", "Invalid profile picture: Size/format issue. Default profile picture applied.");
            });
            
            
        })
        .catch(error => {
            console.error("error"+error);
            showAlert("red","sever side error")

        });
        event.preventDefault();

    }
});


