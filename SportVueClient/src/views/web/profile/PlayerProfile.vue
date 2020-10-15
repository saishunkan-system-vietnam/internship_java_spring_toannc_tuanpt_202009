<template>
  <v-row>
    <v-col cols="12" sm="1" md="1"></v-col>
    <v-col cols="12" sm="12" md="10">
      <v-breadcrumbs :items="items"></v-breadcrumbs>
      <v-row>
        <v-card
          cols="12"
          sm="3"
          md="3"
          class="portrait mt-2 ml-4"
          :img="player.avatar"
          height="120"
          width="120"
        ></v-card>
        <v-col cols="12" sm="3" md="3" >
          <h6>{{ player.name }}</h6>
          <p>
            {{
              player.team != null
                ? player.team.nameTeam
                : "Not in Team"
            }}
          </p>
          <p>Age: {{player.age}}</p>
          <p>Country: {{player.address}}</p>
        </v-col>
        <v-spacer></v-spacer>
        <v-card
          style="margin-right: 15px"
          class="portrait mt-2"
          :img="player.team != null ? player.team.logo : ''"
          height="120"
          width="120"
        >
        </v-card>
      </v-row>
      <div class="mt-4"></div>
      <v-divider></v-divider>

      <strong>Last Matches</strong>

      <v-data-table
        @click:row="handleRowClick"
        :headers="headers"
        :items="desserts"
        :items-per-page="5"
        class="elevation-1"
      >
        <template v-slot:[`item.finalScore`]="{ item }">
          {{ item.scoreTeam1 }} - {{ item.scoreTeam2 }}
        </template>
        <template v-slot:[`item.team1`]="{ item }">
          <p
            style="font-weight: bold"
            class="mb-0"
            v-if="item.teamPlayed === item.idTeam1"
          >
            {{ item.nameTeam1 }}(P)
          </p>
          <p class="mb-0" v-else>{{ item.nameTeam1 }}</p>
        </template>
        <template v-slot:[`item.Vs`]="{}"> Vs </template>
        <template v-slot:[`item.team2`]="{ item }">
          <p
            style="font-weight: bold"
            class="mb-0"
            v-if="item.teamPlayed === item.idTeam2"
          >
            {{ item.nameTeam2 }}(P)
          </p>
          <p class="mb-0" v-else>{{ item.nameTeam2 }}</p>
        </template>
        <template v-slot:[`item.result`]="{ item }">
          <p
            style="color: green"
            class="mb-0"
            v-if="item.teamPlayed === item.idwinner"
          >
            Win
          </p>
          <p style="color: red" class="mb-0" v-else>Lose</p>
        </template>
      </v-data-table>
    </v-col>
    <v-col cols="12" sm="1" md="1"></v-col>
  </v-row>
</template>
<script>
export default {
  data: () => ({
    items: [
      {
        text: "Sport",
        disabled: false,
        href: "/",
      },
      {
        text: "Team",
        disabled: false,
        href: "/",
      },
      {
        text: "Player",
        disabled: true,
        href: "/",
      },
    ],
    headers: [
      {
        text: "Date",
        align: "start",
        value: "timeEnd",
      },
      { text: "Tournament", value: "nameTour" },
      { text: "Team 1", value: "team1" },
      { text: "", value: "Vs" },
      { text: "Team 2", value: "team2" },
      { text: "Final Score", value: "finalScore" },
      { text: "Result", value: "result" },
    ],
    desserts: [],
    player: {},
  }),
  mounted() {
    this.historyMemberMatchs("14");
    this.playerInfo(14);
  },
  methods: {
    historyMemberMatchs(id) {
      let self = this;
      this.$store
        .dispatch("user/historyMemberMatchs", id)
        .then(function (response) {
          // console.log(response.data.payload);
          response.data.payload.forEach((element) => {
            self.desserts = self.desserts.concat(element.schedules);
          });
        })
        .catch(function (error) {});
    },
    playerInfo(id) {
      let self = this;
      this.$store
        .dispatch("user/getPlayerId", id)
        .then(function (response) {
          self.player = response.data;
        })
        .catch(function (error) {});
    },
    handleRowClick(item) {
      alert("You clicked " + item.name);
    },
  },
};
</script>