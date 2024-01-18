import { Component } from '@angular/core';

@Component({
  selector: 'app-registration-form',
  templateUrl: './registration-form.component.html',
  styleUrls: ['./registration-form.component.css']
})
export class RegistrationFormComponent {
  profilePicture: File | null = null;

  // Event listener for profile picture input change
  onProfilePicChange(event: any) {
    this.profilePicture = event.target.files[0];
  }
 
  getProfilePicUrl(): string {
    return this.profilePicture ? URL.createObjectURL(this.profilePicture) : 'assets/default-icon2.jpeg';
  }
}
