var app = new Vue({
    el: "#app",
    data: {
        user: {
            id: "",
            username: "",
            password: "",
            email: "",
            age: "",
            sex: ""
        },
        userList: []
    },
    methods: {
        findAll: function () {
            axios.post("/vuejsdemo/user/findAll")
                .then(function (response) {
                    app.userList = response.data;
                    console.log(response)
                })
                .catch(function (error) {
                    console.log(error)
                })
        },
        findById(id) {
            /*axios.get("/vuejsdemo/user/findById", {params: {id: id}})
                .then(response => {
                    app.user = response.data;
                    $("#myModal").modal("show");
                })
                .catch(error => console.log(error))*/
            $.post("/vuejsdemo/user/findById", {id: id}, function (data) {
                app.user = data;
                $("#myModal").modal("show");
            })
        },
        update(user) {
            axios.post("/vuejsdemo/user/update", app.user)
                .then(() => this.findAll())
                .catch(error => console.log(error))
        }
    },
    created: function () {
        this.findAll()
    }
});