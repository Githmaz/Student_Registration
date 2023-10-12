let studentList = document.getElementById("student-List");
let card = document.getElementById('student-image-card');
let container = document.getElementById('container')
let table = `   <thead>
                    <tr>
                        <th> Student ID </th>
                        <th> Profile pic </th>
                        <th> Student Name </th>
                        <th> Birthday</th>
                        <th> Email </th>
                        <th> Guardian </th>
                    </tr>
                </thead>`;

fetch("http://localhost:8080/Student/List")
  .then(response => response.json())
  .then(json => {
    if (json.length === 0) {
      studentList.innerHTML = `<div> No students found </div>`;
    } else {
      const promises = json.map(element => {
        return fetch(`http://localhost:8080/Student/Image?id=${element.id}`)
          .then(response => response.blob())
          .then(blob => {
            if (blob.size === 0) {
              return 'assets/default-icon.png';
            } else {
              return URL.createObjectURL(blob);
            }
          });
      });

      Promise.all(promises)
        .then(imgUrls => {
          let updatedTable = `<tbody>`;
          json.forEach((element, index) => {
            const imgUrl = imgUrls[index];
            updatedTable += `<tr class="student-row">
              <td>${element.id}</td>
              <td> <img id="profilePicPreview" src="${imgUrl}" alt="Preview"/></td>
              <td>${element.firstName + " " + element.lastName}</td>
              <td>${element.birthday}</td>
              <td>${element.email}</td>
              <td>${element.guardian.firstName + " " + element.guardian.lastName}</td>
            </tr>`;
          });

          updatedTable += `</tbody>`;
          studentList.innerHTML = table + updatedTable;

          // Add event listener to every "rows" element
          let rows = document.getElementsByClassName("student-row");
          for (let i = 0; i < rows.length; i++) {
            rows[i].addEventListener("click", () => {
              fetch(`http://localhost:8080/Student/getStudent?id=${rows[i].querySelector("td:first-child").textContent}`)
              .then(response => response.json())
              .then(json =>{
                card.style.right = '0%';
                container.style.right = '12.5%';
                document.getElementById("student-profile-pic").src = rows[i].querySelector("td:nth-child(2) img").src;
                document.getElementById("Student-fullname").textContent = json.firstName + " " + json.lastName;
                document.getElementById("Student-Phonenumber").textContent = json.phoneNumber;
                document.getElementById("Student-Birthday").textContent = json.birthday;
                document.getElementById("Student-Email").textContent = json.email;
                document.getElementById("Student-address").textContent = json.address;
                document.getElementById("Guardian-name").textContent = json.guardian.firstName+" "+json.guardian.lastName;
                document.getElementById("Guardian-Phonenumber").textContent = json.guardian.phoneNumber;
                document.getElementById("Guardian-career").textContent = json.guardian.career;
              })
            
            });
          }
        })
        .catch(error => {
          console.error(error);
        });
    }
  })
  .catch(error =>{
    studentList.innerHTML = `<div> Sever not found! </div>`;
  });

  // Add an event listener to the document's body
document.body.addEventListener("click", (event) => {
  const card = document.getElementById('student-image-card');
  
  // Check if the click target is not within the card
  if (!card.contains(event.target) && !studentList.contains(event.target)) {
    card.style.right = '-25%';
    container.style.right = '0';
  }
});