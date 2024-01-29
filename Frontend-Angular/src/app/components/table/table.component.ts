import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';


@Component({
  selector: 'app-table',
  templateUrl: './table.component.html',
  styleUrls: ['./table.component.css']
})
export class TableComponent {
  table = `   <thead>
  <tr>
      <th> Student ID </th>
      <th> Profile pic </th>
      <th> Student Name </th>
      <th> Birthday</th>
      <th> Email </th>
      <th> Guardian </th>
  </tr>
</thead>`;

constructor(private http: HttpClient) {}

ngOnInit() {
  this.fetchData() 
}

fetchData() {
  this.http.get<JSON>("http://localhost:8080/Student/List").subscribe(
    (data) => {
      // Handled successful response
      console.log(data); // Log the data to see the structure
      // Assuming data is an array of student objects, you can iterate over it and append to the table
      // data.forEach((student: any) => {
      //   this.table += `
      //     <tr>
      //       <td>${student.id}</td>
      //       <td><img src="${student.profilePic}" alt="Profile pic"></td>
      //       <td>${student.name}</td>
      //       <td>${student.birthday}</td>
      //       <td>${student.email}</td>
      //       <td>${student.guardian}</td>
      //     </tr>`;
      // });
    },
    (error) => {
      // Handle error
      console.error('Error fetching data:', error);
    }
  );
}
}

  
