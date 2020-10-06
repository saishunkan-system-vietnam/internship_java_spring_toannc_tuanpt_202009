<template>
  <div class="container">
    <v-row>
      <v-col class="d-flex" cols="12" sm="4"
        ><router-link to="/sports">All Sport</router-link>
      </v-col>
      <v-col class="d-flex" cols="12" sm="4"><router-link to="/soccer">Soccer</router-link></v-col>
      <v-col class="d-flex" cols="12" sm="4"><router-link to="/baseball">Base Ball</router-link></v-col>
    </v-row>
    <router-view></router-view>
  </div>
</template>