<template>
  <v-app id="inspire">
    <v-navigation-drawer v-model="drawer" app>
      <v-img
        style="margin-top: 50px"
        max-height="300"
        max-width="250"
        src="@/assets/unnamed.png"
      ></v-img>
      <h2 class="text-center">ADMIN</h2>
      <v-list dense>
        <router-link to="/admin/home" style="text-decoration: none">
          <v-list-item >
            <v-list-item-action>
              <v-icon>mdi-home</v-icon>
            </v-list-item-action>
              <v-list-item-title> Home </v-list-item-title>
          </v-list-item>
        </router-link>
        <router-link to="/LayoutUser" style="text-decoration: none">
          <v-list-item >
            <v-list-item-action>
              <v-icon>mdi-account</v-icon>
            </v-list-item-action>
              <v-list-item-title> User </v-list-item-title>
          </v-list-item>
        </router-link>
        <router-link to="/LayoutSchedule" style="text-decoration: none">
          <v-list-item >
            <v-list-item-action>
              <v-icon>mdi-calendar</v-icon>
            </v-list-item-action>
              <v-list-item-title> Schedule </v-list-item-title>
          </v-list-item>
        </router-link>
        <router-link to="/LayoutTeam" style="text-decoration: none">
          <v-list-item >
            <v-list-item-action>
              <v-icon>mdi-account-group</v-icon>
            </v-list-item-action>
      
              <v-list-item-title> Team </v-list-item-title>
           
          </v-list-item>
        </router-link>
        <router-link to="/LayoutTournament" style="text-decoration: none">
          <v-list-item >
            <v-list-item-action>
              <v-icon>mdi-tournament</v-icon>
            </v-list-item-action>
              <v-list-item-title> Tournament </v-list-item-title>
          </v-list-item>
        </router-link>
      </v-list>
    </v-navigation-drawer>

    <v-app-bar app color="indigo" dark>
      <v-app-bar-nav-icon @click.stop="drawer = !drawer">
        <b-icon-list font-scale="2"></b-icon-list
      ></v-app-bar-nav-icon>
      <v-toolbar-title>Sports</v-toolbar-title>
      <v-spacer></v-spacer>

      <v-btn
        style="background-color: White; color: black"
        @click.prevent="logout"
      >
        Back To Web
        <v-icon right>exit_to_app</v-icon>
      </v-btn>
    </v-app-bar>

    <v-main>
      <v-container>
        <router-view />
      </v-container>
    </v-main>
    <v-footer color="indigo" app>
      <span class="white--text">&copy; {{ new Date().getFullYear() }}</span>
    </v-footer>
    <v-overlay :value="$store.state.auth.overlay">
      <v-progress-circular indeterminate size="64"></v-progress-circular>
    </v-overlay>
  </v-app>
</template>
           
<script>
export default {
  props: {
    source: String,
  },
  data: () => ({
    drawer: null,
  }),
  methods: {
    logout: function () {
      this.$router.push("/");
    },
  },
};
</script>