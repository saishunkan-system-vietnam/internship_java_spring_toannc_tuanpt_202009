<template>
    <v-card color="grey lighten-4 mb-1" flat tile>
      <v-toolbar>
        <div class="container" >
          <v-row >
            <v-col class="pt-4 pb-3">
              <v-toolbar-title><a>Dash Board</a></v-toolbar-title>
            </v-col>
            <v-spacer></v-spacer>
            <!-- <template v-if="isLoggedIn">
            <template>
              <v-row class="d-flex" justify="center">
                <v-menu
                  v-model="showMenu"
                  absolute
                  offset-y
                  style="max-width: 600px"
                >
                  <template v-slot:activator="{ on, attrs }">
                    <v-card
                      class="portrait"
                      img="https://cdn.vuetifyjs.com/images/cards/girl.jpg"
                      height="20"
                      width="20"
                      v-bind="attrs"
                      v-on="on"
                    ></v-card>
                  </template>

                  <v-list>
                    <v-list-item v-for="(item, index) in items" :key="index">
                      <v-list-item-title>{{ item.title }}</v-list-item-title>
                    </v-list-item>
                  </v-list>
                </v-menu>
              </v-row>
            </template>
          </template> -->
            <v-col class="d-flex" cols="1" >
              <template>
                <v-dialog v-model="LoginDialog" max-width="600px">
                  <template v-slot:activator="{ on, attrs }">
                    <v-btn color="primary" dark v-bind="attrs" v-on="on">
                      Login
                      <v-icon right>exit_to_app</v-icon>
                    </v-btn>
                  </template>

                  <Login />
                </v-dialog>
              </template>
            </v-col>
            <v-col class="d-flex ml-5" cols="2">
              <template>
                <v-dialog v-model="RegisterDialog" max-width="600px">
                  <template v-slot:activator="{ on, attrs }">
                    <v-btn color="primary" dark v-bind="attrs" v-on="on">
                      Register
                      <v-icon right>assignment_late</v-icon>
                    </v-btn>
                  </template>

                  <Register />
                </v-dialog>
              </template>
            </v-col>
          </v-row>
        </div>
      </v-toolbar>
    </v-card>

    <!-- <v-navigation-drawer v-model="drawer" class="indigo">
      <p>Test</p>
    </v-navigation-drawer>-->

</template>

<script>
import Register from "@/views/web/Register.vue";
import Login from "@/views/web/Login.vue";
import store from "@/store";

export default {
  name: "navbar",
  components: {
    Login,
    Register,
  },
  data() {
    return {
      LoginDialog:false,
      RegisterDialog:false,
      drawer: false,
      dialog: false,
      items: [
        {
          title: "click1",
        },
        {
          title: "click2",
        },
      ],
    };
  },
  computed: {
    isLoggedIn: function () {
      return this.$store.getters.isLoggedIn;
    },
  },
};
</script>
<style>

</style>