<template>
  <v-card color="grey lighten-4 mb-1" flat tile>
    <v-toolbar>
      <div class="container">
        <v-row>
          <v-col class="pt-4 pb-3">
            <v-toolbar-title class="pl-6">
              <a :href="$router.resolve({ name: 'web' }).href"
                >Sports</a
              ></v-toolbar-title
            >
          </v-col>
          <v-spacer></v-spacer>
          <template v-if="isProfile">
            <v-row class="d-flex" justify="center">
              <v-menu
                v-model="showMenu"
                absolute
                offset-y
                style="max-width: 600px"
              >
                <template v-slot:activator="{ on, attrs }">
                  <p class="pt-4 pr-2">{{ profile.profile.name }}</p>
                  <v-card
                    class="portrait mt-2"
                    :img="profile.profile.avatar"
                    height="40"
                    width="40"
                    v-bind="attrs"
                    v-on="on"
                  ></v-card>
                </template>

                <v-list>
                  <v-list-item>
                    <v-list-item-title>
                      <div
                        class="fixButton row-pointer"
                        @click="roleFunction(profile.role)"
                      >
                        Profile
                      </div>
                    </v-list-item-title>
                  </v-list-item>
                  <v-list-item>
                    <v-list-item-title
                      ><div class="fixButton row-pointer" @click="logout"
                        >Logout</div
                      ></v-list-item-title
                    >
                  </v-list-item>
                </v-list>
              </v-menu>
            </v-row>
          </template>
          <template v-else-if="isAdminProfile">
            <v-row class="d-flex" justify="center">
              <v-menu
                v-model="showMenu"
                absolute
                offset-y
                style="max-width: 600px"
              >
                <template v-slot:activator="{ on, attrs }">
                  <p class="pt-4 pr-2">{{ profile.profile.name }}</p>
                  <v-card
                    class="portrait mt-2"
                    :img="profile.profile.avatar"
                    height="40"
                    width="40"
                    v-bind="attrs"
                    v-on="on"
                  ></v-card>
                </template>

                <v-list>
                  <v-list-item>
                    <v-list-item-title>
                      <div class="fixButton row-pointer" @click="toAdminPage">
                        Admin Page
                      </div>
                    </v-list-item-title>
                  </v-list-item>
                  <v-list-item>
                    <v-list-item-title>
                      <div
                        class="fixButton ml-4 row-pointer"
                        @click="roleFunction(profile.role)"
                      >
                        Profile
                      </div>
                    </v-list-item-title>
                  </v-list-item>
                  <v-list-item>
                    <v-list-item-title
                      ><div class="fixButton ml-4 row-pointer" @click="logout">
                        Logout
                      </div></v-list-item-title
                    >
                  </v-list-item>
                </v-list>
              </v-menu>
            </v-row>
          </template>

          <template v-else
            ><v-col class="d-flex" cols="1">
              <template>
                <v-dialog v-model="LoginDialog" max-width="600px">
                  <template v-slot:activator="{ on, attrs }">
                    <v-btn color="primary" dark v-bind="attrs" v-on="on">
                      Login
                      <v-icon right>exit_to_app</v-icon>
                    </v-btn>
                  </template>
                  <Login
                    :closeLoginDialog="closeLoginDialog"
                    :checkProfile="checkProfile"
                  />
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

                  <Register :closeRegisterDialog="closeRegisterDialog" />
                </v-dialog>
              </template> </v-col
          ></template>
        </v-row>
      </div>
    </v-toolbar>

    <v-dialog
      v-model="modalMember"
      hide-overlay
      max-width="1200px"
      style="background: floralwhite"
    >
      <MemberProfile
        :controlModalMember="controlModalMember"
        :showMenu="showMenu"
      />
    </v-dialog>

    <v-dialog v-model="modalUser" max-width="600px">
      <UserProfile :controlModalUser="controlModalUser" :showMenu="showMenu" />
    </v-dialog>
  </v-card>
</template>

<script>
import Register from "@/views/web/Register.vue";
import Login from "@/views/web/Login.vue";
import store from "@/store";
import MemberProfile from "@/views/web/profile/MemberProfile";
import UserProfile from "@/views/web/profile/UserProfile";

export default {
  name: "navbar",
  components: {
    Login,
    Register,
    MemberProfile,
    UserProfile,
  },
  data() {
    return {
      modalMember: false,
      modalUser: false,
      LoginDialog: false,
      RegisterDialog: false,
      showMenu: false,
    };
  },
  computed: {
    isProfile: function () {
      return this.$store.state.user.isProfile;
    },
    isAdminProfile: function () {
      return this.$store.state.user.isAdminProfile;
    },
    //do this need to check bind (:) in html up
    profile: function () {
      // console.log("Should Run last");
      // console.log(this.$store.state.user.userInfo);
      if (this.$store.state.user.userInfo != null) {
        return this.$store.state.user.userInfo;
      } else {
        return null;
      }
    },
  },
  mounted() {
    // console.log(this.$store.state.user.userInfo.profile)
  },
  methods: {
    closeLoginDialog() {
      this.LoginDialog = false;
    },
    closeRegisterDialog: function () {
      this.RegisterDialog = false;
    },
    checkProfile: function () {
      this.$store.commit("user/user_profile");
    },
    checkProfileAdmin: function () {
      this.$store.commit("user/admin_profile");
    },
    logout() {
      this.showMenu = false;
      this.$store.dispatch("auth/logout").then(() => {
        const role = this.$store.state.user.userInfo.role;
        if (role === "ROLE_ADMIN") {
          this.checkProfileAdmin();
        } else if (role === "ROLE_USER" || role === "ROLE_MEMBER") {
          this.checkProfile();
        }
        this.LoginDialog = false;
        this.$router.push("/").catch((err) => {});
      });
    },
    roleFunction(role) {
      console.log(role);
      if (role === "ROLE_MEMBER") {
        this.controlModalMember();
      } else {
        this.controlModalUser();
      }
    },
    controlModalMember() {
      this.modalMember = !this.modalMember;
    },
    controlModalUser() {
      this.modalUser = !this.modalUser;
    },
    toAdminPage() {
      this.$router.push("/admin/home");
    },
    // setBlank() {
    //   console.log(this.$refs);
    //   this.$refs.refLogin.test();
    // },
  },
};
</script>

<style lang="css">
  .v-list-item .row-pointer:hover {
    cursor: pointer;
  }
  .fixButton {
    background: white !important;
  }
</style>