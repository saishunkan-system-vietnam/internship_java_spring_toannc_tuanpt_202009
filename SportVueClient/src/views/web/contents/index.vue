<template>
  <div class="container">
    <v-row class="fixLayout" style="margin: 0">
        <v-col cols="12" sm="1" style="text-align: center" >
      </v-col>
      <v-col cols="12" sm="2" style="text-align: center" >
        <router-link to="/sports" style="color: white;text-decoration: none;">All Sport</router-link>
      </v-col>
      <v-col cols="12" sm="2" style="text-align: center"
        ><router-link to="/soccer" style="color: white;text-decoration: none;"
          ><v-icon medium color="green darken-2"> mdi-soccer </v-icon>  Soccer</router-link
        ></v-col
      >
      <v-col cols="12" sm="2" style="text-align: center"
        ><router-link to="/baskestBall" style="color: white;text-decoration: none;"
          ><v-icon medium color="orange darken-2"> sports_basketball </v-icon>  Baskest Ball</router-link
        ></v-col
      >
      <v-col cols="12" sm="2" style="text-align: center"
        ><router-link to="/tableTennis" style="color: white;text-decoration: none;"
          ><v-icon medium color="blue darken-2"> sports_tennis </v-icon>  Table Tennis
        </router-link></v-col
      >
<<<<<<< HEAD

      <v-col cols="12" sm="2" style="text-align: center"><router-link to="/tableTennis">More</router-link></v-col>
        <v-col cols="12" sm="1" style="text-align: center" >
      </v-col>
=======
      <v-col cols="12" sm="2" style="text-align: center"
        ><router-link to="/baskestBall" style="color: white;text-decoration: none;"><v-icon medium color="yellow darken-2"> sports_baseball </v-icon>  Base Ball</router-link></v-col
      >
      <v-col cols="12" sm="2"><router-link to="/tableTennis">More</router-link></v-col>
>>>>>>> 750eaa9a32807b42be0b19cd7f65e8dce36f31c7
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
  padding: 16px 15px;
}
</style>