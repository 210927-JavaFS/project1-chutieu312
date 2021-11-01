const URL = "http://localhost:8081/";

//===============================================================================
// Get all the button

let buttonRow = document.getElementById("buttonRow");

let userButton = document.createElement("button");
let reimbButton =  document.createElement("button");
let viewPastTicketsButton = document.createElement("button");
let addReimbRequestButton = document.createElement("button");

let addReimbButton = document.getElementById('addReimbButton');
let updateReimbButton = document.getElementById('updateReimbButton');
let loginButton = document.getElementById('loginButton');
let filterReimbButton = document.getElementById('filterReimbButton');

let logoutButton = document.createElement('button');
let findReimbButton = document.createElement('button');
let approveDenyReimbButton = document.createElement('button');

//===============================================================================
//Assign all f need to do to each button
//Not buttonRow bc it just a <div for add button into 

userButton.onclick = getUsers;
reimbButton.onclick = getReimbs;

logoutButton.onclick = logout;
loginButton.onclick = loginToApp; 

addReimbButton.onclick = addReimb;
updateReimbButton.onclick = updateReimb;
findReimbButton.onclick = findReimbByStatus;


//viewPastTicketsButton.onclick = displayPastTickets;
addReimbRequestButton.onclick = displayAddNewReimbMenu;
approveDenyReimbButton.onclick = displayApproveDenyReimb;

//===============================================================================
// Assign text for each button

userButton.innerText = "View All Users";
userButton.className="btn btn-info";
reimbButton.innerText = "View all reimbursement for all employees";
reimbButton.className="btn btn-primary";
logoutButton.innerText = "Logout";
logoutButton.className="btn btn-warning";
viewPastTicketsButton.innerText = "View Past Tickets";
viewPastTicketsButton.className="btn btn-info";
addReimbRequestButton.innerText = "Add Reimbursement Request";
addReimbRequestButton.className="btn btn-success";
findReimbButton.innerText = "Filter Request by Status";
findReimbButton.className="btn btn-success";
approveDenyReimbButton.innerText = "Approve/deny reimbursement"
approveDenyReimbButton.className="btn btn-primary";

// <p> Welcome for each time login
let welcome;
let loginFail;
//===============================================================================

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

    if(loginFail){
      document.getElementsByClassName("formClass")[0].removeChild(loginFail);
    }

    let data = await response.json();
    console.log(data);

    welcome = document.createElement("h5");
    welcome.setAttribute("style", "color:blue")
    welcome.innerText = "Hello, "+ data.firstName + " "+data.lastName+",            "+data.userRole.role+" ID: "+data.userId;
    document.getElementsByClassName("row")[0].appendChild(welcome);

    if (data.userRole.roleId==1) {
      employeeMenu(data);
    }
    else {
      financeMenu(data);
    }
  }
  else{
    loginFail = document.createElement("p");
    loginFail.setAttribute("style", "color:red")
    loginFail.innerText = "LOGIN FAILED"
    document.getElementsByClassName("formClass")[0].appendChild(loginFail);
  }
}
//===============================================================================

function employeeMenu(user){

  buttonRow.appendChild(viewPastTicketsButton);
  buttonRow.appendChild(addReimbRequestButton);
  buttonRow.appendChild(logoutButton);
  document.getElementById("buttonRow").style.display = 'block';

  
  viewPastTicketsButton.onclick =  async function (){
    let response = await fetch(URL+"reimbs/authors/"+user.userId.toString(), {
      credentials:"include"
    });
  
    if (response.status===200) {
      let data = await response.json();
      console.log(data);
      populateReimbTable(data);
      document.getElementById("reimb-table").style.display = 'block';
    }
  }
}

//===============================================================================

function displayAddNewReimbMenu(){
  document.getElementById("addReimb").style.display = 'block';
}

//===============================================================================

function displayApproveDenyReimb(){
  document.getElementById("updateReimb").style.display = 'block';
}

//===============================================================================

function findReimbByStatus(){
  console.log("findReimbByStatus run");
  document.getElementById("filterReimb").style.display = 'block';
  //let filterStatus = document.getElementById("filterStatus").value;

  //filterReimbButton.onclick = filterReimb(filterStatus);
}
//===============================================================================

filterReimbButton.onclick = filterReimb;

async function filterReimb(){

  let filterStatus = document.getElementById("filterStatus").value;

  let response = await fetch(URL+"reimbs/status/"+filterStatus,{
    credentials:"include"
  });
  if (response.status===200) {
          let data = await response.json();
    console.log(data);
    populateReimbTable(data);
    document.getElementById("reimb-table").style.display = 'block';
  }

}

//===============================================================================

function financeMenu(user){

  buttonRow.appendChild(userButton);
  buttonRow.appendChild(reimbButton);
  buttonRow.appendChild(findReimbButton);
  buttonRow.appendChild(approveDenyReimbButton);
  buttonRow.appendChild(logoutButton);

  document.getElementById("buttonRow").style.display = 'block';
  //document.getElementById("addReimb").style.display = 'block';

}

//===============================================================================


//===============================================================================



//===============================================================================

async function logout(){

  let response = await fetch(URL+"logout", {
    method:"POST",
    credentials:"include"
  });
  if(response.status===200){
    console.log("logout sucessfully");
    //document.getElementsByClassName("row")[0].innerHTML = '';
    document.getElementById("buttonRow").style.display = 'none';
    document.getElementById("addReimb").style.display = 'none';
    document.getElementById("updateReimb").style.display = 'none';
    document.getElementById("user-table").style.display = 'none';
    document.getElementById("reimb-table").style.display = 'none';
    document.getElementById("filterReimb").style.display = 'none';
    // document.getElementById("user-table").innerHTML='';
    // document.getElementById("reimb-table").innerHTML='';
    // document.getElementById("addReimb").innerHTML='';

    //document.getElementsByClassName("row")[0].removeChild(welcome);
    let first = buttonRow.firstElementChild;
    while (first) {
      first.remove();
      first = buttonRow.firstElementChild
    }

    
    //para=null;
  }
}

//===============================================================================

async function getUsers(){
  let response = await fetch(URL+"users", {credentials:"include"});

  if(response.status === 200){
    let data = await response.json();
    populateUsersTable(data);
    document.getElementById("user-table").style.display = 'block';
  }else{
    console.log("The Users cannot be accessed");
  }
}
//===============================================================================

function populateUsersTable(data){
  let tbody = document.getElementById("userBody");

  tbody.innerHTML="";

  for(let user of data){
    let row = document.createElement("tr");

    for(let cell in user){
      let td = document.createElement("td");
      if (cell!="userRole") td.innerText=user[cell];
      else if (user[cell]) td.innerText=`${user[cell].role}`;
      row.appendChild(td);
    }
    tbody.appendChild(row);
  }
}
//===============================================================================

async function getReimbs(){
  let response = await fetch(URL+"reimbs", {credentials:"include"});
  if(response.status===200){
    let data = await response.json();
    populateReimbTable(data);
    document.getElementById("reimb-table").style.display = 'block';
  }else{
    console.log("Reimbs not available.");
  }
}
//===============================================================================

function populateReimbTable(data){
  let tbody = document.getElementById("reimbBody");

  //tbody.innerHTML="";
  console.log(data);

  while(tbody.rows.length > 0) {
    tbody.deleteRow(0);
  }

  for(let reimb of data){
    let row = document.createElement("tr");
    for(let cell in reimb){
      let td = document.createElement("td");
      if (cell!="author" && cell!="resolver" && cell!="reimbStatus" && cell!="reimbType") {
        td.innerText = reimb[cell];
      }
      if (cell=="author") td.innerText = `${reimb[cell].userRole.role} : ${reimb[cell].userId}`;
      if (cell == "resolver" && reimb[cell]!=null) td.innerText = `${reimb[cell].userRole.role} : ${reimb[cell].userId}`;
      if (cell=="reimbStatus") td.innerText = `${reimb[cell].status}`;
      if (cell=="reimbType") td.innerText = `${reimb[cell].type}`;
      if(cell == "submittedlDate") td.innerText = `${new Date(reimb[cell])}`;
      if(cell == "resolvedlDate")
        if (reimb[cell]!=null) td.innerText = `${new Date(reimb[cell])}`;

      row.appendChild(td);
    }
    tbody.appendChild(row);
  }
}
//===============================================================================

function getNewReimb(){
  let newReimbAmount = document.getElementById("reimbAmount").value;
  let newReimbDescription = document.getElementById("reimbDescription").value;
  let newReimbReceipt= document.getElementById("reimbReceipt").value;
  let newReimbType = document.getElementById("reimbType").value;

  let reimbType;
  if (newReimbType === 'Lodging') {
    reimbType={
      typeId: 1,
      type: 'Lodging'
    }
  } 
  else if (newReimbType === 'Travel') {
      reimbType={
        typeId: 2,
        type: 'Travel'
      }
  } else if (newReimbType === 'Food') {
    reimbType={
      typeId: 3,
      type: 'Food'
    }
  } else if (newReimbType === 'Other') {
    reimbType={
      typeId: 4,
      type: 'Other'
    }
  }

  let reimb =  {
    amount:newReimbAmount,
    description:newReimbDescription,
    receipt:newReimbReceipt,
    reimbType
  }
    return reimb;
}
//===============================================================================

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

//===============================================================================

function getReimbApproveDeny(){
  let newReimbId = document.getElementById("reimbId").value;
  let newReimbStatus = document.getElementById("reimbStatus").value;


  let reimbApproveDeny =  {
    reimbId:newReimbId,
    reimbStatus:newReimbStatus
  }
    return reimbApproveDeny;
}

//===============================================================================

async function updateReimb(){
  let reimbApproveDeny = getReimbApproveDeny();

  let response = await fetch(URL+"reimbs", {
    method:'PUT',
    body:JSON.stringify(reimbApproveDeny),
    credentials:"include"
  });

  if(response.status===200){
    console.log("Reimb updated successfully.");
  }else{
    console.log("Something went wrong updating your Reimb.")
  }

}

