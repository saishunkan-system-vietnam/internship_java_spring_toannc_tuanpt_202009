<template>
  <div class="container">
    <v-row>

      <v-col  cols="12" sm="4"
        ><router-link to="/sports">All Sport</router-link>
      </v-col>
      <v-col cols="12" sm="4"><router-link to="/soccer">Soccer</router-link></v-col>
      <v-col  cols="12" sm="4"><router-link to="/baseball">Base Ball</router-link></v-col>
       <v-col class="d-flex" cols="12" sm="2"><router-link to="/">Baskest Ball</router-link></v-col>
      <v-col class="d-flex" cols="12" sm="2"><router-link to="/">More</router-link></v-col>

    </v-row>
    <div style="background-color:white;
    padding-top: 0px;
">
    <router-view></router-view>
    </div>
  </div>
</template>