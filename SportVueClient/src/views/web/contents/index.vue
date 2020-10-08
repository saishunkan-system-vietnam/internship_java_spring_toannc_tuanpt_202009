<template>
  <div class="container">
    <v-row class="fixLayout" justify="center" align="center">
      <v-col cols="12" sm="1"></v-col>
      <v-col cols="12" sm="2">
        <router-link to="/sports" style="color: white"> All Sport</router-link>
      </v-col>
      <v-col cols="12" sm="2"
        ><router-link to="/soccer" style="color: white"
          >Soccer</router-link
        ></v-col
      >
      <v-col cols="12" sm="2"
        ><router-link to="/baskestBall" style="color: white"
          >Baskest Ball</router-link
        ></v-col
      >
      <v-col cols="12" sm="2"
        ><router-link to="/tableTennis" style="color: white"
          >Table Tennis
        </router-link></v-col
      >
      <v-col cols="12" sm="2"
        ><router-link to="/" style="color: white">Base Ball</router-link></v-col
      >
      <v-col cols="12" sm="1"><router-link to="/">More</router-link></v-col>
    </v-row>
    <div style="background-color: white; padding-top: 0px">
      <router-view></router-view>
    </div>
  </div>
</template>
<script>
export default {};
</script>
<style >
.fixLayout {
  background-color: black;
  margin: 0;
}
.router-link-active {
  color: black !important;
  background-color: white !important;
  padding: 15px 70px 15px 0px !important;
}
</style>