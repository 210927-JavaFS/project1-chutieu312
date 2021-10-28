const URL = "http://localhost:8081/";

let buttonRow = document.getElementById("buttonRow");
let userButton = document.createElement("button");
let reimbButton = document.createElement("button");
let addReimbButton = document.getElementById('addReimbButton');
let loginButton = document.getElementById('loginButton');

userButton.onclick = getUsers;
reimbButton.onclick = getReimbs;
addReimbButton.onclick = addReimb;
loginButton.onclick = loginToApp; 

userButton.innerText = "Users Assemble";
reimbButton.innerText = "See Reimbs";

async function loginToApp(){
  let user = {
    username:document.getElementById("username").value,
    password:document.getElementById("password").value
  }

  let response = await fetch(URL+"login", {
    method:"POST",
    body:JSON.stringify(user),
    credentials:"include" //This will save the cookie when we receive it. 
  });

  if(response.status===200){
    document.getElementsByClassName("formClass")[0].innerHTML = '';
    buttonRow.appendChild(userButton);
    buttonRow.appendChild(reimbButton);
  }
  else{
    let para = document.createElement("p");
    para.setAttribute("style", "color:red")
    para.innerText = "LOGIN FAILED"
    document.getElementsByClassName("formClass")[0].appendChild(para);
  }
}

async function getUsers(){
  let response = await fetch(URL+"users", {credentials:"include"});

  if(response.status === 200){
    let data = await response.json();
    populateUsersTable(data);
  }else{
    console.log("The Users cannot be accessed");
  }
}

function populateUsersTable(data){
  let tbody = document.getElementById("userBody");

  tbody.innerHTML="";

  for(let user of data){
    let row = document.createElement("tr");

    for(let cell in user){
      let td = document.createElement("td");
      td.innerText=user[cell];
      row.appendChild(td);
    }
    tbody.appendChild(row);
  }
}

async function getReimbs(){
  let response = await fetch(URL+"reimbs", {credentials:"include"});
  if(response.status===200){
    let data = await response.json();
    populateReimbTable(data);
  }else{
    console.log("Reimbs not available.");
  }
}

function populateReimbTable(data){
  let tbody = document.getElementById("reimbBody");

  tbody.innerHTML="";

  for(let reimb of data){
    let row = document.createElement("tr");
    for(let cell in reimb){
      let td = document.createElement("td");
      td.innerText = reimb[cell];
      row.appendChild(td);
    }
    tbody.appendChild(row);
  }
}

function getNewReimb(){
  let newReimbAmount = document.getElementById("reimbAmount").value;
  let newReimbSubmitted = document.getElementById("reimbSubmitted").value; 
  let newReimbResolved = document.getElementById("reimbResolved").value;
  let newReimbDescription = document.getElementById("reimbDescription").value;
  let newReimbReceipt= document.getElementById("reimbReceipt").value;
  let newReimbAuthor = document.getElementById("reimbAuthor").value;
  let newReimbResolver = document.getElementById("reimbResolver").value;
  let newReimbStatusID = document.getElementById("reimbStatusID").value;
  let newReimbTypeID = document.getElementById("reimbTypeID").value;
  

  let reimb =  {
    reimbAmount:newReimbAmount,
    reimbSubmitted:newReimbSubmitted,
    reimbResolved:newReimbResolved,
    reimbDescription:newReimbDescription,
    reimbReceipt:newReimbReceipt,
    reimbAuthor:newReimbAuthor,
    reimbResolver:newReimbResolver,
    reimbStatusID:newReimbStatusID,
    reimbTypeID:newReimbTypeID

  }

  return reimb;
}

async function addReimb(){
  let reimb = getNewReimb();

  let response = await fetch(URL+"reimbs", {
    method:'POST',
    body:JSON.stringify(reimb),
    credentials:"include"
  });

  if(response.status===201){
    console.log("Reimb created successfully.");
  }else{
    console.log("Something went wrong creating your Reimb.")
  }

}