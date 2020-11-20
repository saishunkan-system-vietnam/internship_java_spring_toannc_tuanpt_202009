<template>
  <div>
    <v-row class="pl-10">
      <v-col cols="11" md="1" style="margin-left: 80px"></v-col>
      <v-col cols="11" md="9">
        <v-img
          max-height="200px"
          min-width="140px"
       
          :src="
            tournament.banner != ''
              ? baseUrl + tournament.banner
              : 'data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAASYAAACsCAMAAADhRvHiAAAAQlBMVEX////8/Pz4+Pj09PTx8fH29vb///3p6en///v49/r98rz9/Prq5uHz8OyxsbHt7PDi5uv37vL1+vz9/+zV1Nf7+OH4MhMRAAAC20lEQVR4nO3d3VrbMAyAYZyWjg4YDOj93+pONiDLn53Ilix97wEP0KepLNtymibp3V1gP9cfTm2iMOlb2y96UYRG3jNcHh8XHjE+e4cfQhvKa+fTs9DLNTb80o6gD8ZHOwz3kN3IvrxoB7BFPol7tngVjwJwo4dKV8RdgwAUMVoDjoZltFlmkB8A8EqowveyUPQSp5hwDd5nI01kUdq9g5QmPj3NMbwWPsHB0Gjh1Pj1dLuFQVGAZAHKmITFfmsHgF6ozq4qLz7U2Kg/Jbk/BazBtZscMKWtpPvdT30TDKOBpDSK3qaHbGZyHn6IzxzYKs5J68MTJmRnKYVe7bMbr1UnsGHaL417qteBkSa/wCu6GDJYB7EpLfyONUwtTDAmEEjAgxEPD9oRAEAA7E5pqJP1RG/GZbXrrcYFiEpn7QjqYRIjJkZ+RSQ3BKl7nX5Jnz8gy8aNDPU3nbn9KhHEPgNxovOPgeWrHzBif4IUR1j2BPsJsMDzEYqaZpfjykNOpKuYFsB/huxZEbpcnk1eJRa6S3BM7sTn7TcWWD0WsRaWyZDtBcWs74K9gSPEbcNWRGxzhwRL479NWV1Ij5hrksNmKuk0k+xWZOq0f0sEaOIq5kIZ8gWgGIXDmOgrP7CAqYEC/u4unmP/7YedEMm8/1Ij2cKBvcgcG6ek2D5jZW28ND0BqOVY818GRJAmABZZq03W4lFnKCGGQgEArLrw9d053rUDqEfqonm+CD7LtdY+UuFpnuyq5SFPWQTTRMabWCzX0fM/bv+zUhTfGLg8eGZMjP+1ufjnXwt+xO7vOpaJrnArap/67XlhvbIQrSA5vGDHYZME8Kn/LpejN8zQXlDbTAYO7WyiKu3TSd5YdbIUZslrUk22K63+ib/GeeGuvhhh2igi+eFca7318LVH9+GjNbebdgS2LPRqxSz1OI4WpvJC5RBvYY8pA5DrpH2YV0DdKlXhbTJlVdAfcqQC5niL5u4AAAAASUVORK5CYII='
          "
        ></v-img>
        <v-toolbar-title class="pl-5">
          <h1
            style="font-weight: 500; line-height: 34px; color: #2b2c2d"
            class="text-center"
          >
            {{ tournament.nameTournament }}-<b style="color: blue">{{
              tournament.status == 0
                ? "Up Comming"
                : tournament.status == 1
                ? "On Game"
                : "Finished"
            }}</b>
          </h1>
          <h5
            class="pt-3 text-center"
            style="font-size: 12px; font-weight: 400"
          >
            <v-icon>mdi-alarm-check</v-icon>{{ tournament.timeStart }}/{{
              tournament.timeEnd
            }}
          </h5>
        </v-toolbar-title>
      </v-col>
    </v-row>
    <v-divider style="margin: 0 !important"></v-divider>
    <v-row class="pl-6">
      <v-col cols="12" md="2" xm="2"></v-col>
      <v-col>
        <div>
          <nav id="nav">
            <router-link
              :to="{
                path: `/tournamentDetail/${tournament.idTournament}/team`,
              }"
              style="cursor: pointer"
              >Rank</router-link
            >
            <router-link
              :to="{
                path: `/tournamentDetail/${tournament.idTournament}/results`,
              }"
              style="cursor: pointer"
              >Results</router-link
            >
            <router-link
              :to="{
                path: `/tournamentDetail/${tournament.idTournament}/fixtures`,
              }"
              style="cursor: pointer"
              >Fixtures</router-link
            >
          </nav>
          <hr />
        </div>
      </v-col>
    </v-row>
    <v-col cols="12" md="2" xm="2"></v-col>

    <v-col>
      <v-row>
        <v-col cols="12" sm="10"> <router-view></router-view></v-col> </v-row
    ></v-col>
  </div>
</template>
<script>
import { ENV } from "@/config/env.js";

export default {
  data() {
    return {
      tournament: {},
    };
  },
  computed: {
    baseUrl() {
      return ENV.BASE_IMAGE;
    },
  },
  methods: {},
  async created() {
    await this.$store
      .dispatch("tournament/getById", this.$route.params.id)
      .then((response) => {
        this.tournament = response.data.payload;
      });
  },
};
</script>
<style scoped>
#nav {
  display: flex;
  align-items: center;
}
#nav a {
  font-weight: bold;
  color: red;
  padding: 0 10px;
}
#nav a.vue-school-active-class {
  color: green;
}
</style>