<template>
  <div class="container">
    <v-row class="fixLayout" style="margin: 0">
      
      <v-col cols="12" sm="2" style="text-align: center" >
        <router-link to="/sports" style="color: white"> All Sport</router-link>
      </v-col>
      <v-col cols="12" sm="2" style="text-align: center"
        ><router-link to="/soccer" style="color: white"
          >Soccer</router-link
        ></v-col
      >
      <v-col cols="12" sm="2" style="text-align: center"
        ><router-link to="/baskestBall" style="color: white"
          >Baskest Ball</router-link
        ></v-col
      >
      <v-col cols="12" sm="2" style="text-align: center"
        ><router-link to="/tableTennis" style="color: white"
          >Table Tennis
        </router-link></v-col
      >
      <v-col cols="12" sm="2" style="text-align: center"
        ><router-link to="/baskestBall" style="color: white">Base Ball</router-link></v-col
      >
      <v-col cols="12" sm="2"><router-link to="/tableTennis">More</router-link></v-col>
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
  background-color: #252C35;
}
.router-link-active {
  color: black !important;
  background-color: white !important;
  padding: 15px 30px;
}
</style>