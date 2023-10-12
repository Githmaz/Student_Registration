// Account log in 
let usernameInput = document.getElementById("usernameInput");
let usernameAvailability = document.getElementById("usernameAvailability");
let passwordInput = document.getElementById("passwordInput");

let loginButton = document.getElementById('login-button');

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
    }, 5000);
}


// Event listener for  username
usernameInput.addEventListener("input", async () => {
    try {
        const response = await fetch("http://localhost:8080/Student/CheckDuplicateUsername?username=" + usernameInput.value);
        const isDuplicate = await response.json();

        if (!isDuplicate) {
            usernameAvailability.textContent = "Username doesn't exit.";
            usernameAvailability.style.color = "red";
        } else {
            usernameAvailability.textContent = "Username found!";
            usernameAvailability.style.color = "green";
        }
    } catch (error) {
        console.error("Error checking username ", error);
    }
});

form.addEventListener('submit', async (event) => {
    event.preventDefault(); 
    
     // Check if the usernameAvailability indicator is green
     const correctUsername = usernameAvailability.style.color === 'green';
     if(correctUsername){
        const url = `http://localhost:8080/Student/Login?username=${usernameInput.value}&password=${passwordInput.value}`;
        try {
            const response = await fetch(url);
            const json = await response.json();
            if(json.id===0){
                showAlert("red","Invalid Password")
            }else{
                showAlert("green","Log in succesfull")
            }
        } catch (error) {
            showAlert("red","! Sever not found")
        }
     }else{

        showAlert("red","! Invalid Username")

     }

});


