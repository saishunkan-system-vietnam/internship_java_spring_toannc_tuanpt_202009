<template>
  <div>
    <b-overlay
      :show="busy"
      rounded
      opacity="0.6"
      spinner-small
      spinner-variant="primary"
      ><h1 class="text-center">User</h1>
     
      <b-row>
        <b-col cols="12" sm="6">
          Username
          <b-form-input
            v-model="textUsername"
            placeholder="Input Field"
          ></b-form-input>
        </b-col>
        <b-col cols="12" sm="6">
          Role
          <b-form-select v-model="selectedRole" :options="optionsRole">
          </b-form-select>
        </b-col>
      </b-row>
      <b-row>
        <b-col cols="12" sm="6">
          Email
          <b-form-input
            v-model="textEmail"
            placeholder="Input Field"
          ></b-form-input>
        </b-col>
        <b-col cols="12" sm="6">
          <br />
          <b-button variant="primary" @click="search">Search</b-button>
        </b-col>
      </b-row>

      <b-table
        id="my-table"
        :per-page="perPage"
        :current-page="currentPage"
        striped
        hover
        :items="items"
        :fields="fields"
      >
        <template v-slot:cell(Acction)="row">
          <router-link :to="{ path: '/DetailTournament/' + row.item.idTour }">
            <v-btn fab small>
              <v-icon>mdi-forward</v-icon>
            </v-btn>
          </router-link>
        </template>
        <template v-slot:cell(role)="row">
          <p
            :style="
              row.item.role == 'ROLE_USER'
                ? 'color:green'
                : row.item.role == 'ROLE_MEMBER'
                ? 'color:blue'
                : 'color:red'
            "
          >
            {{
              row.item.role == "ROLE_USER"
                ? "USER"
                : row.item.role == "ROLE_MEMBER"
                ? "MEMBER"
                : "ADMIN"
            }}
          </p>
        </template>
        <template v-slot:cell(Detail)="row">
          <router-link
            :to="{ path: '/DetailUser/' + row.item.id }"
            id="${user.id}"
          >
            <b-icon-chevron-double-right></b-icon-chevron-double-right>
          </router-link>
        </template>
      </b-table>

      <b-pagination
        v-model="currentPage"
        :total-rows="rows"
        :per-page="perPage"
        aria-controls="my-table"
      ></b-pagination>
    </b-overlay>
  </div>
</template>
<
<script>
export default {
  data() {
    return {
      busy: true,
      selectedRole:null,
      textUsername:"",
      textEmail:"",
      perPage: 10,
      currentPage: 1,
      loading: false,
      rows: "",
      fields: [
        {
          key: "id",
          label: "No",
          sortable: true,
        },
        {
          key: "username",
          label: "Username",
          sortable: true,
        },
        {
          key: "email",
          label: "Email",
          sortable: true,
        },
        {
          key: "role",
          label: "Role",
          sortable: true,
          sortDirection: "desc",
        },
        "Detail",
      ],
      items: [
       
      ],
      user:[],
      optionsRole: [
        { value: null, text: "Please select an option" },
        { value: "ROLE_ADMIN", text: "Admin" },
        { value: "ROLE_MEMBER", text: "Member" },
        { value: "ROLE_USER", text: "User" },
      ]
   
    };
  },
  created() {
    this.getListUser();
  },
  methods: {
    getListUser() {
        this.$store.dispatch("user/getAll").then(res=>{
          this.items=res.data;
          this.busy = false;
          this.rows=res.data.length;
          this.user=res.data
        })
    },
    search() {
      var arrSearch = [];
      {
        if (this.textUsername != "") {
          this.user.forEach((element) => {
            if (element.username.includes(this.textUsername)) {
              arrSearch.push(element);
            }
          });
        } else {
          arrSearch = this.user;
        }
      }
      if (this.textEmail != null) {
        var arrEmail = [];
        arrSearch.forEach((element) => {
          if (element.email.includes(this.textEmail)) {
            arrEmail.push(element);
          }
        });
        arrSearch = arrEmail;
      }
      if (this.selectedRole != null) {
        var arrRole = [];
        arrSearch.forEach((element) => {
          if (element.role == this.selectedRole) {
            arrRole.push(element);
          }
        });
        arrSearch = arrRole;
      }
      
      this.items = arrSearch;
      this.rows=arrSearch.length
    },
  },
};
</script>