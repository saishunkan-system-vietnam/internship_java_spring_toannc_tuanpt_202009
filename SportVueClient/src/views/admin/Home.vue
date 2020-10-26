<template>
  <v-app id="inspire">
    <v-navigation-drawer v-model="drawer" app >
   
      <h1 class="text-center" style="margin-top:50px">ADMIN</h1>
      <v-list dense>

        <v-list-item class="css" @click="home">
          <v-list-item-action>
            <v-icon>mdi-home</v-icon>
          </v-list-item-action>
          <v-list-item-content>
            <v-list-item-title> Home </v-list-item-title>
          </v-list-item-content>
        </v-list-item>
        <v-list-item class="css" @click="User">
          <v-list-item-action>
            <v-icon>mdi-account</v-icon>
          </v-list-item-action>
          <v-list-item-content>
            <v-list-item-title> User </v-list-item-title>
          </v-list-item-content>
        </v-list-item>
        <v-list-item class="css" @click="Schedule">
          <v-list-item-action>
            <v-icon>mdi-calendar</v-icon>
          </v-list-item-action>
          <v-list-item-content>
            <v-list-item-title> Schedule </v-list-item-title>
          </v-list-item-content>
        </v-list-item>
        <v-list-item class="css" @click="Team">
          <v-list-item-action>
            <v-icon>mdi-account-group</v-icon>
          </v-list-item-action>
          <v-list-item-content>
            <v-list-item-title> Team </v-list-item-title>
          </v-list-item-content>
        </v-list-item>
        <v-list-item class="css" @click="Tournament">
          <v-list-item-action>
            <v-icon>mdi-tournament</v-icon>
          </v-list-item-action>
          <v-list-item-content>
            <v-list-item-title> Tournament </v-list-item-title>
          </v-list-item-content>
        </v-list-item>

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
    
  </v-app>
</template>
           
<script>
export default {
  props: {
    source: String,
  },
  data: () => ({
    drawer: false,
  }),
  methods: {
    logout: function () {
      this.$router.push("/");
    },
    home(){
      this.$router.push('/admin/home').catch (err => {})
    },
    User(){
      this.$router.push('/LayoutUser').catch (err => {})
    },
    Schedule(){
      this.$router.push('/LayoutSchedule').catch (err => {})
    },
    Team(){
      this.$router.push('/LayoutTeam').catch (err => {})
    },
    Tournament(){
      this.$router.push('/LayoutTournament').catch (err => {})
    },
    
  },
};
</script>
<style>
.css:hover {
  color: #fff;
  cursor: pointer;
}
</style>