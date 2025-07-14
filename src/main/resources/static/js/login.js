fetch("/api/accounts/Martin")
.then(response => {
    if (response.ok){
        return response.json()
    }
    throw new Error('account doesn\'t exist')
})
.then(data =>{
    console.log(data)
})
.catch(error => {
    alert(error)
})