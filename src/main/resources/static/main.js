class Employee {
    constructor(employeeId, firstName, lastName, jobTitle, departmentId) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.jobTitle = jobTitle;
        this.departmentId = departmentId;
    }

    getTableRow() {
        return `<tr>
        <th scope="row">${this.employeeId}</th>
        <td>${this.firstName}</td>
        <td>${this.lastName}</td>
        <td>${this.jobTitle}</td>
        <td>${this.departmentId}</td>
        <td>
        <button onclick="deleteEmployee(${this.employeeId})" type="button" class="btn btn-danger">Delete</button>
        <button data-bs-toggle="modal" data-bs-target="#modal${this.employeeId}" type="button" class="btn btn-warning">Edit</button>
        <div class="modal fade" id="modal${this.employeeId}" tabindex="-1">
          <div class="modal-dialog">
            <div class="modal-content">
              <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Edit employee</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
              </div>
              <div class="modal-body">
                    <div id="employee-form">
                        <div class="mb-3">
                            <label for="emp-id${this.employeeId}" class="form-label">Employee id</label>
                            <input value="${this.employeeId}" type="text" class="form-control" id="emp-id${this.employeeId}" disabled>
                        </div>
                        <div class="mb-3">
                            <label for="first-name-edit-${this.employeeId}" class="form-label">Name</label>
                            <input value="${this.firstName}" type="text" class="form-control" id="first-name-edit-${this.employeeId}">
                        </div>
                        <div class="mb-3">
                            <label for="last-name-edit${this.employeeId}" class="form-label">Surname</label>
                            <input value="${this.lastName}" type="text" class="form-control" id="last-name-edit-${this.employeeId}">
                        </div>
                        <div class="mb-3">
                            <label for="job-title-edit-${this.employeeId}" class="form-label">Job title</label>
                            <input value="${this.jobTitle}" type="text" class="form-control" id="job-title-edit-${this.employeeId}">
                        </div>
                        <div class=" mb-3">
                            <label for="department-id-edit-${this.employeeId}" class="form-label">Departament</label>
                            <select id="department-id-edit-${this.employeeId}" class="form-select">
                                <option selected value="1">IT</option>
                                <option value="2">HR</option>
                                <option value="3">Sales</option>
                            </select>
                        </div>
                    </div>
              </div>
              <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                <button type="button" class="btn btn-primary" onclick="validateEditForm(${this.employeeId})" data-bs-dismiss="modal">Save changes</button>
              </div>
            </div>
          </div>
        </div>
        </td>
        </tr>`
    }
}

class EmployeeRequest {
    constructor(firstName, lastName, jobTitle, departmentId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.jobTitle = jobTitle;
        this.departmentId = departmentId;
    }
}

const getEmployees = () => {
    async function getData(url) {
        const response = await fetch(url, {
            headers: {
                'Content-Type': 'application/json'
            },
        });
        return response.json();
    }

    getData('/api/employee')
        .then(data => {
            populateEmployeeTable(data);
        });
}

const putEmployee = (data) => {
    async function putData(url, data = {}) {
        await fetch(url, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data)
        });
    }

    putData('/api/employee', data)
        .then(() => {
            getEmployees();
        });
}

const postEmployee = (data) => {
    async function postData(url, data = {}) {
        await fetch(url, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data)
        });
    }

    postData('/api/employee', data)
        .then(() => {
            getEmployees();
        });
}

const deleteEmployee = (id) => {
    async function delData(url) {
        await fetch(url, {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json'
            }
        });
    }

    delData('/api/employee/' + id)
        .then(() => {
            getEmployees();
        });
}

const populateEmployeeTable = (employeeList) => {
    const list = document.getElementById('employee-table');
    let result = '';
    employeeList.forEach(employee => {
        const employee1 = new Employee(employee.employeeId, employee.firstName, employee.lastName, employee.jobTitle, employee.departmentId);
        result += employee1.getTableRow();
    })
    list.innerHTML = result;
}

const validateForm = () => {
    const firstName = document.getElementById('first-name').value;
    const lastName = document.getElementById('last-name').value;
    const jobTitle = document.getElementById('job-title').value;
    const departmentId = document.getElementById('department-id').value;
    if (isBlank(firstName) || isBlank(lastName) || isBlank(jobTitle) || isBlank(departmentId)) {
        alert('Please fill all fields');
        return;
    }
    const employeeRequest = new EmployeeRequest(firstName, lastName, jobTitle, departmentId);
    postEmployee(employeeRequest)
}

const validateEditForm = (id) => {
    const firstName = document.getElementById('first-name-edit-' + id).value;
    const lastName = document.getElementById('last-name-edit-' + id).value;
    const jobTitle = document.getElementById('job-title-edit-' + id).value;
    const departmentId = document.getElementById('department-id-edit-' + id).value;
    if (isBlank(firstName) || isBlank(lastName) || isBlank(jobTitle) || isBlank(departmentId)) {
        alert('Please fill all fields');
        return;
    }
    const empId = document.getElementById('emp-id' + id).value;
    const employee = new Employee(empId, firstName, lastName, jobTitle, departmentId);
    putEmployee(employee);
}

const isBlank = (input) => {
    return !input || input.length === 0;
}

getEmployees();