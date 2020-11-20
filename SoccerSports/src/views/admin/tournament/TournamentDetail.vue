<template>
  <div>
    <v-breadcrumbs :items="linkTournamentDetail" large>
      <template v-slot:divider>
        <v-icon>mdi-chevron-right</v-icon>
      </template>
    </v-breadcrumbs>

    <div class="text-center">
      <v-img
        max-height="200px"
        min-width="140px"
        :src="
          tournament.banner != ''
            ? baseUrl + tournament.banner
            : 'data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAASYAAACsCAMAAADhRvHiAAAAQlBMVEX////8/Pz4+Pj09PTx8fH29vb///3p6en///v49/r98rz9/Prq5uHz8OyxsbHt7PDi5uv37vL1+vz9/+zV1Nf7+OH4MhMRAAAC20lEQVR4nO3d3VrbMAyAYZyWjg4YDOj93+pONiDLn53Ilix97wEP0KepLNtymibp3V1gP9cfTm2iMOlb2y96UYRG3jNcHh8XHjE+e4cfQhvKa+fTs9DLNTb80o6gD8ZHOwz3kN3IvrxoB7BFPol7tngVjwJwo4dKV8RdgwAUMVoDjoZltFlmkB8A8EqowveyUPQSp5hwDd5nI01kUdq9g5QmPj3NMbwWPsHB0Gjh1Pj1dLuFQVGAZAHKmITFfmsHgF6ozq4qLz7U2Kg/Jbk/BazBtZscMKWtpPvdT30TDKOBpDSK3qaHbGZyHn6IzxzYKs5J68MTJmRnKYVe7bMbr1UnsGHaL417qteBkSa/wCu6GDJYB7EpLfyONUwtTDAmEEjAgxEPD9oRAEAA7E5pqJP1RG/GZbXrrcYFiEpn7QjqYRIjJkZ+RSQ3BKl7nX5Jnz8gy8aNDPU3nbn9KhHEPgNxovOPgeWrHzBif4IUR1j2BPsJsMDzEYqaZpfjykNOpKuYFsB/huxZEbpcnk1eJRa6S3BM7sTn7TcWWD0WsRaWyZDtBcWs74K9gSPEbcNWRGxzhwRL479NWV1Ij5hrksNmKuk0k+xWZOq0f0sEaOIq5kIZ8gWgGIXDmOgrP7CAqYEC/u4unmP/7YedEMm8/1Ij2cKBvcgcG6ek2D5jZW28ND0BqOVY818GRJAmABZZq03W4lFnKCGGQgEArLrw9d053rUDqEfqonm+CD7LtdY+UuFpnuyq5SFPWQTTRMabWCzX0fM/bv+zUhTfGLg8eGZMjP+1ufjnXwt+xO7vOpaJrnArap/67XlhvbIQrSA5vGDHYZME8Kn/LpejN8zQXlDbTAYO7WyiKu3TSd5YdbIUZslrUk22K63+ib/GeeGuvhhh2igi+eFca7318LVH9+GjNbebdgS2LPRqxSz1OI4WpvJC5RBvYY8pA5DrpH2YV0DdKlXhbTJlVdAfcqQC5niL5u4AAAAASUVORK5CYII='
        "
      ></v-img>
      <h1>{{ tournament.nameTournament }}</h1>
      <v-btn @click.stop="dialog = true"
        v-if="tournament.status == 0"
        style="right: 130px; position: absolute; background: dodgerblue"
        >Edit</v-btn
      >
      {{ tournament.timeStart }}<v-icon>mdi-arrow-right-bold</v-icon
      >{{ tournament.timeEnd }}<br />
      <div
        :style="
          tournament.status == 0
            ? 'color:green'
            : tournament.status == 1
            ? 'color:blue'
            : 'color:red'
        "
      >
        {{
          tournament.status == 0
            ? "Up Comming"
            : tournament.status == 1
            ? "On Game"
            : "Finished"
        }}
      </div>
    </div>
    <div style="margin-top: 50px">
      <v-tabs v-model="tab" background-color="transparent" color="basil" grow>
        <v-tab>Team </v-tab> <v-tab>Schedule </v-tab> <v-tab>Rank </v-tab>
      </v-tabs>

      <v-tabs-items v-model="tab">
        <v-tab-item>
          <v-card color="basil" flat>
            <TournamentTeam :getData="getData" :tournament="tournament" />
          </v-card>
        </v-tab-item>
        <v-tab-item>
          <v-card color="basil">
            <v-card-text
              ><TournamentSchedule
                :getDataTournament="getData"
                :tournament="tournament"
            /></v-card-text>
          </v-card>
        </v-tab-item>
        <v-tab-item>
          <v-card color="basil" flat>
            <v-card-text
              ><TournamentRank :getData="getData" :tournament="tournament"
            /></v-card-text>
          </v-card>
        </v-tab-item>
      </v-tabs-items>
    </div>
    <v-dialog v-model="dialog" max-width="800px">
      <v-card>
        <v-card-title class="headline">
          Edit Tournament 
        </v-card-title>

        <v-card-text>
          <TournamentEdit :getData="getData" :tournament="tournament" :hide="hide"></TournamentEdit>
        </v-card-text>

        <v-card-actions>
          <v-spacer></v-spacer>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </div>
</template>
<script>
import { ENV } from "@/config/env.js";

import TournamentSchedule from "./TournamentSchedule";
import TournamentTeam from "./TournamentTeam";
import TournamentRank from "./TournamentRank";
import TournamentEdit from "./TournamentEdit";
export default {
  data: () => {
    return {
      dialog: false,
      tournament: {},
      tab: null,
      linkTournamentDetail: [
        {
          text: "Dashboard",
          disabled: false,
          href: "/admin",
        },
        {
          text: "Tournament",
          disabled: false,
          href: "/admin/tournament",
        },
      ],
    };
  },
  components: {
    TournamentSchedule,
    TournamentTeam,
    TournamentRank,
    TournamentEdit,
  },
  computed: {
    baseUrl() {
      return ENV.BASE_IMAGE;
    },
  },
  created() {
    this.getData();
  },
  mounted() {
    this.$store.commit("auth/auth_overlay_true");
    this.$store
      .dispatch("tournament/getById", this.$route.params.id)
      .then((response) => {
        this.$store.commit("auth/auth_overlay_false");
        if (response.data.code == 0) {
          this.tournament = response.data.payload;
        }
      });
  },
  methods: {
    hide(){
      this.dialog=!this.dialog
    },
    getData() {
      this.$store.commit("auth/auth_overlay_true");
      this.$store
        .dispatch("tournament/getById", this.$route.params.id)
        .then((response) => {
          this.$store.commit("auth/auth_overlay_false");
          if (response.data.code == 0) {
            this.tournament = response.data.payload;
          }
        });
    },
  },
};
</script>