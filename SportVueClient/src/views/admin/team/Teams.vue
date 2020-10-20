<template>
  <v-data-table
    @click:row="handleRowClick"
    :headers="headers"
    :items="desserts"
    sort-by="nameTeam"
    class="elevation-1 container row-pointer"
    :search="search"
    :custom-sort="customSort"
  >
    <template v-slot:top>
      <v-toolbar flat color="white">
        <v-row>
          <router-link :to="{ path: '/create', query: { maxId: 'maxTeamId' } }">
            <v-btn color="primary" dark class="mb-2"> New Team </v-btn>
          </router-link>
          <v-divider class="mx-4" inset vertical></v-divider>
          <v-spacer></v-spacer>
        </v-row>
        <v-row>
          <v-col cols="12" sm="4" md="4">
            <v-select
              v-model="search"
              :items="items"
              label="Standard"
            ></v-select>
          </v-col>
          <v-col cols="12" sm="4" md="4">
            <v-text-field
              v-model="search"
              append-icon="mdi-magnify"
              label="Search"
              single-line
              hide-details
            ></v-text-field>
          </v-col>
          <v-col cols="12" sm="4" md="4">
            <v-text-field
              v-model="search"
              append-icon="mdi-magnify"
              label="Search"
              single-line
              hide-details
            ></v-text-field>
          </v-col>
        </v-row>
      </v-toolbar>
    </template>
    <template v-slot:[`item.nameTour`]="{ item }">
      <template v-if="item.idTour != 0 && item.tournament != null">
        {{ item.tournament.nameTour }}
      </template>
      <template style="color: green" v-else> Available </template>
    </template>
    <template v-slot:[`item.logo`]="{ item }">
      <img
        :src="item.logo"
        alt=""
        width="100px"
        height="70px"
        style="margin: 3px 0 3px 0"
      />
    </template>
    <template v-slot:[`item.actions`]="{ item }">
      <v-icon class="mr-2" @click="editTeam(item)"> mdi-pencil </v-icon>
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
        { text: "Current Tournament", value: "nameTour" },
        { text: "Total Matchs", value: "totalmatch" },
        { text: "Total Wins", value: "totalwin" },
        { text: "Team Detail", value: "actions", sortable: false },
      ],
      desserts: [],
      maxTeamId: 0,
      items:["Football", "TableTennis", "Baseball", "Basketball"],
    };
  },
  mounted() {
    let self = this;
    axios
      .get("http://localhost:8090/api/v1/team/getAll")
      .then(function (response) {
        // console.log(response.data)
        self.desserts = response.data;
        self.maxTeamId =
          1 +
          Math.max.apply(
            Math,
            response.data.map(function (item) {
              return item.idTeam;
            })
          );
      })
      .catch(function (error) {
        console.log(error);
      });
  },
  watch: {
    
  },
  methods: {
    editTeam(item) {
      // console.log(item);
      this.$router.push({ name: "TeamDetail", params: { id: item.idTeam } });
    },
    handleRowClick(item) {
      this.editTeam(item);
    },
    customSort(items, index, isDesc) {
      items.sort((a, b) => {
        if (index === "date") {
          if (!isDesc) {
            return dateHelp.compare(a.date, b.date);
          } else {
            return dateHelp.compare(b.date, a.date);
          }
        } else {
          if (!isDesc) {
            return a[index] < b[index] ? -1 : 1;
          } else {
            return b[index] < a[index] ? -1 : 1;
          }
        }
      });
      return items;
    },
  },
};
</script>

<style lang="css" scoped>
.remove-padding > div {
  padding: 0;
}
.row-pointer >>> tbody tr :hover {
  cursor: pointer;
}
.v-data-table-header__icon {
  opacity: 1;
}
</style>