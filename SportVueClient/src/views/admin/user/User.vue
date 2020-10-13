<template>
  <div data-app>
    <b-container fluid>
      <div>
        <v-row>
          <v-col cols="12" sm="6" md="3">
            <v-text-field label="Search" v-model="nameSearch"></v-text-field>
          </v-col>

          <v-col cols="12" sm="6" md="3">
            <v-select
              :items="items"
              item-text="value"
              item-value="type"
              label="Option Search"
              v-model="type"
            ></v-select>
          </v-col>

          <v-col cols="12" sm="6" md="3">
            <b-button variant="outline-primary" @click.prevent="Search">
              <b-icon-search></b-icon-search>Search
            </b-button>
          </v-col>

          <v-col cols="12" sm="6" md="3"></v-col>
        </v-row>
      </div>
      <table class="table">
        <thead>
          <tr>
            <th scope="col">id</th>
            <th scope="col">UserName</th>
            <th scope="col">Email</th>
            <th scope="col">Role</th>
            <th scope="col">Profile</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="user in user" :key="user.id">
            <th scope="row">{{ user.id }}</th>
            <td>{{ user.username }}</td>
            <td>{{ user.email }}</td>
            <td>
              {{
                user.role == "ROLE_USER"
                  ? "USER"
                  : user.role == "ROLE_ADMIN"
                  ? "ADMIN"
                  : "MEMBER"
              }}
            </td>

            <td>
              <router-link :to="{ path: '/Detail/' + user.id }" id="${user.id}">
                <b-icon-chevron-double-right></b-icon-chevron-double-right>
              </router-link>
            </td>
          </tr>
        </tbody>
      </table>
    </b-container>
    <div>
      <b-pagination
        align="center"
        v-model="page"
        :total-rows="pages"
        :per-page="perPage"
        first-number
        last-number
      ></b-pagination>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      perPage: 1,
      pages: 10,
      user: [],
      page: 1,
      pagesize: 5,
      nameSearch: "",
      items: [
        { type: "account_username", value: "username" },
        { type: "account_email", value: "email" },
        { type: "account_role", value: "role" },
      ],
      type: "",
      loadingSearch: false,
    };
  },
  created() {
    this.getListUser();
  },
  methods: {
    getListUser() {
      let params = {
        page: this.page,
        pageSize: this.pagesize,
        type: this.type,
        nameSearch: this.nameSearch,
      };

      this.$store.dispatch("user/searchUser", params).then((response) => {
        this.user = response.data.account;
        this.pages = response.data.totalPage;
      });
    },
    Search() {
      this.getListUser();
      this.loadingSearch=true
    },
  },
  watch: {
    page() {
      if (!this.loadingSearch) {
        (this.nameSearch = ""), (this.type = "");
      }
      this.getListUser();
    },
  },
};
</script>