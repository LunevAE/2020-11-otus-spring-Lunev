// GET all
fetch('/books').then(response => response.json().then(console.log))

// GET one
fetch('/books/2').then(response => response.json().then(console.log))

// POST add new one
fetch('/books', { method: 'POST', headers: {'Content-Type': 'application/json'}, body: JSON.stringify({title: 'Oblomov'})}).then(result => console.log(result))

// DELETE 
fetch('/books/1', { method: 'DELETE'}).then(result => console.log(result))


