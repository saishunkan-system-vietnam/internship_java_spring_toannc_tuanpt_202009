<template>
  <v-data-table
    :headers="headers"
    :items="desserts"
    sort-by="nameTeam"
    class="elevation-1"
    :search="search"
  >
    <template v-slot:top>
      <v-toolbar flat color="white">
        <router-link :to="{ path: '/create', query: { maxId: 'maxTeamId' } }">
          <v-btn color="primary" dark class="mb-2"> New Team </v-btn>
        </router-link>

        <v-divider class="mx-4" inset vertical></v-divider>
        <v-spacer></v-spacer>

        <v-text-field
          v-model="search"
          append-icon="mdi-magnify"
          label="Search"
          single-line
          hide-details
        ></v-text-field>
      </v-toolbar>
    </template>
    <template v-slot:[`item.nameTour`]="{ item }">
      {{
        item.idTour != 0 || item.idTour != null
          ? item.tournament.nameTour
          : "Available"
      }}
    </template>
    <template v-slot:[`item.logo`]="{ item }">
      <img
        :src="`http://localhost:8090/${item.logo}`"
        alt=""
        width="100px"
        height="50px"
        style="margin: 3px 0 3px 0"
      />
    </template>
    <template v-slot:[`item.actions`]="{ item }">
      <v-icon small class="mr-2" @click="editTeam(item)"> mdi-pencil </v-icon>
    </template>
  </v-data-table>
</template>

<script>
import axios from "axios";

export default {
  data() {
    return {
      search: "",
      headers: [
        {
          text: "Logo",
          align: "start",
          sortable: false,
          value: "logo",
        },
        { text: "Team Name", value: "nameTeam" },
        { text: "Type", value: "type" },
        { text: "Current Tournament", value: "tourName" },
        { text: "Total Matchs", value: "totalmatch" },
        { text: "Total Wins", value: "totalwin" },
        { text: "Actions", value: "actions", sortable: false },
      ],
      desserts: [],
      maxTeamId: 0,
    };
  },
  mounted() {
    let self = this;
    axios
      .get("http://localhost:8090/api/v1/team/getAll")
      .then(function (response) {
        self.desserts = response.data;
        // console.log(response.data);
        self.maxTeamId =
          1 +
          Math.max.apply(
            Math,
            response.data.map(function (item) {
              return item.idTeam;
            })
          );

        // console.log(self.maxTeamId);
      })
      .catch(function (error) {
        // handle error
        console.log(error);
      });
  },

  methods: {
    editTeam(item) {
      console.log(item);
      this.$router.push({ name: "TeamDetail", params: {id: item.idTeam } });
    },
  },
};
</script>

<style>
</style>
