import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-registration-form',
  templateUrl: './registration-form.component.html',
  styleUrls: ['./registration-form.component.css']
})
export class RegistrationFormComponent implements OnInit {
  registrationForm !: FormGroup;
  profilePicture: File | null = null;

  constructor(private formBuilder: FormBuilder, private http: HttpClient) {}
  usernameAvailabilityStatus: string = '';
  usernameAvailabilityColor: string = '';
  
  ngOnInit() {
    this.registrationForm = this.formBuilder.group({
      // Account log in info
      username: [''],
      password: [''],

      // Personal data
      firstName: [''],
      lastName: [''],
      email: [''],
      phoneNumber: [''],
      birthDate: [''],
      address: [''],

      // Guardian's data
      guardianFirstName: [''],
      guardianLastName: [''],
      guardianCareer: [''],
      guardianPhoneNumber: [''],
    });
  }

  // Event listener for profile picture input change
  onProfilePicChange(event: any) {
    this.profilePicture = event.target.files[0];
  }
  checkUsername(event: any) {
    const typedValue = event.target.value;
    this.http.get<boolean>("http://localhost:8080/Student/CheckDuplicateUsername", { params: { username: typedValue } })
    .subscribe(isDuplicate => {
      if(true){
        console.log(isDuplicate)
      }
    }, error => {
      console.error("Error checking username ", error);
    });
  }
  getProfilePicUrl(): string {
    return this.profilePicture ? URL.createObjectURL(this.profilePicture) : 'assets/default-icon2.jpeg';
  }

  onSubmit() {
    if (true) {
      // Form data is valid, build JSON object
      const formData = {
        accountInfo: {
          username: this.registrationForm.value.username,
          password: this.registrationForm.value.password,
        },
        personalData: {
          firstName: this.registrationForm.value.firstName,
          lastName: this.registrationForm.value.lastName,
          email: this.registrationForm.value.email,
          phoneNumber: this.registrationForm.value.phoneNumber,
          birthDate: this.registrationForm.value.birthDate,
          address: this.registrationForm.value.address,
        },
        guardianData: {
          guardianFirstName: this.registrationForm.value.guardianFirstName,
          guardianLastName: this.registrationForm.value.guardianLastName,
          guardianCareer: this.registrationForm.value.guardianCareer,
          guardianPhoneNumber: this.registrationForm.value.guardianPhoneNumber,
        },
        // profilePicture: this.profilePicture,
      };

      // Log the JSON object to the console (you can send it to the server or perform other actions here)
      console.log(formData);
    }
  }
}
