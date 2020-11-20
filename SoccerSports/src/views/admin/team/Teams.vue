<template>
  <v-col>
    <v-breadcrumbs :items="teamLink" large>
      <template v-slot:divider>
        <v-icon>mdi-chevron-right</v-icon>
      </template>
    </v-breadcrumbs>
    <v-data-table
      @click:row="handleRowClick"
      :headers="headers"
      :items="desserts"
      class="elevation-1 container row-pointer"
      :options.sync="options"
    >
      <template v-slot:top>
        <v-toolbar flat color="white">
          <template>
            <v-dialog v-model="createTeamDialog" persistent max-width="1000px">
              <template v-slot:activator="{ on, attrs }">
                <v-btn color="primary" dark v-bind="attrs" v-on="on">
                  New Team
                </v-btn>
              </template>
              <CreateTeam
                :getTeams="getTeams"
                :closeCreateTeamDialog="closeCreateTeamDialog"
              />
            </v-dialog>
          </template>

          <v-divider class="mx-4" inset vertical></v-divider>
          <v-row>
            <v-col cols="0" sm="0" md="1"> </v-col>
            <v-col cols="2" sm="3" md="3">
              <!-- <v-text-field
                v-model="dateSearch"
                append-icon="mdi-magnify"
                label="Date Search"
                single-line
                hide-details
                class="pt-3"
              ></v-text-field> -->
              <v-text-field
                v-model="countrySearch"
                append-icon="mdi-magnify"
                label="Country Search"
                single-line
                hide-details
                class="pt-3"
              ></v-text-field>
            </v-col>
            <v-col cols="2" sm="3" md="3">
              <v-text-field
                v-model="nameTeamSearch"
                append-icon="mdi-magnify"
                label="Team Search"
                single-line
                hide-details
                class="pt-3"
              ></v-text-field>
            </v-col>
            <v-col cols="2" sm="3" md="3">
              <v-select
                v-model="tourNameSearch"
                label="Tournament Search"
                :items="tourItems"
                item-text="nameTournament"
                item-value="nameTournament"
                class="pt-3"
              ></v-select>
            </v-col>
            <v-col cols="2" sm="3" md="1">
              <v-btn class="mt-3" color="error" dark @click="reset"> Reset </v-btn>
            </v-col>
          </v-row>
        </v-toolbar>
        <v-divider class="mt-4 mb-8"></v-divider>
      </template>

      <template v-slot:[`item.tourName`]="{ item }">
        <template v-if="item.idTour != 0 || item.tournament != null">
          <div style="color: red">{{ item.tourName }}</div>
        </template>
        <template v-else><div style="color: green">Available</div></template>
      </template>

      <template v-slot:[`item.logo`]="{ item }">
        <img
          :src="baseUrl + item.logo"
          width="100px"
          height="100px"
          style="margin: 3px 0 3px 0"
        />
      </template>
      <template v-slot:[`item.rate`]="{ item }">
        {{
          item.rate == 0 || item.rate == undefined ? 0 : item.rate.toFixed(2)
        }}
        %
      </template>
      <template v-slot:[`item.nameTeam`]="{ item }">
        <h4>{{ item.nameTeam }}</h4>
      </template>
    </v-data-table>
  </v-col>
</template>

<script>
import { ENV } from "@/config/env.js";
import CreateTeam from "@/views/admin/team/CreateTeam";

export default {
  components: {
    CreateTeam,
  },
  data() {
    return {
      options: {},
      createTeamDialog: false,
      search: "",
      tourItems: [],
      headers: [
        {
          text: "Logo",
          align: "start",
          sortable: false,
          value: "logo",
        },
        { text: "Create Date", value: "createDate", filter: this.dateFilter },
        { text: "Team Name", value: "nameTeam", filter: this.nameTeamFilter },
        { text: "Country", value: "country", filter: this.countryTeamFilter },
        {
          text: "Tournament",
          value: "tourName",
          filter: this.nameTourFilter,
        },
        {
          text: "Members Quatity",
          value: "profile.length",
        },
        { text: "Total Matchs", value: "totalmatch" },
        { text: "Total Wins", value: "totalwin" },
        { text: "Win Rate", value: "rate" },
      ],
      desserts: [],
      maxTeamId: 0,
      nameTeamSearch: "",
      dateSearch: "",
      tourNameSearch: "",
      countrySearch: "",
      teamLink: [
        {
          text: "Dashboard",
          disabled: false,
          href: "/admin",
        },
        {
          text: "Teams",
          disabled: false,
          href: "/admin/teams",
        },
      ],
    };
  },

  mounted() {
    this.getTeams();
  },

  computed: {
    baseUrl() {
      return ENV.BASE_IMAGE;
    },
  },

  watch: {
    nameTeamSearch() {
      this.options.page = 1;
    },
    tourNameSearch() {
      this.options.page = 1;
    },
    countrySearch() {
      this.options.page = 1;
    },
  },

  methods: {
    getTeams() {
      let self = this;
      this.$store.commit("auth/auth_overlay_true");
      this.$store
        .dispatch("team/getTeams")
        .then((response) => {
          this.$store.commit("auth/auth_overlay_false");
          if (response.data.code === 0) {
            self.desserts = response.data.payload;
            self.getTours();
          } else {
            alert(response.data.message);
          }
        })
        .catch(function (error) {
          this.$store.commit("auth/auth_overlay_false");
          alert(error);
        });
    },

    getTours() {
      let self = this;
      self.$store.commit("auth/auth_overlay_true");
      this.$store
        .dispatch("tournament/getAll")
        .then((response) => {
          self.$store.commit("auth/auth_overlay_false");
          if (response.data.code == 0 && response.data.payload.length > 0) {
            self.tourItems = response.data.payload.filter((t) => {
              return t.status != 2;
            });
          } else {
            console.log("Run here Teams Client");
            self.isHavedata = false;
          }
        })
        .catch(function (error) {
          self.$store.commit("auth/auth_overlay_false");
          console.log("Run here Teams Client");
          alert(error);
        });
    },

    editTeam(item) {
      this.$router.push({ path: `/admin/team/detail/${item.idTeam}` });
    },

    handleRowClick(item) {
      this.editTeam(item);
    },

    nameTeamFilter(value) {
      // If this filter has no value we just skip the entire filter.
      if (!this.nameTeamSearch) {
        return true;
      }
      return value.toLowerCase().includes(this.nameTeamSearch.toLowerCase());
    },

    nameTourFilter(value) {
      if (!this.tourNameSearch) {
        return true;
      }
      if (value != undefined) return value == this.tourNameSearch;
    },

    countryTeamFilter(value) {
      if (!this.countrySearch) {
        return true;
      }
      if (this.countrySearch == "") {
        return true;
      }
      return value.toLowerCase().includes(this.countrySearch.toLowerCase());
    },

    dateFilter(value) {
      if (!this.dateSearch) {
        return true;
      }
      if (value != undefined)
        return value.toLowerCase().includes(this.dateSearch.toLowerCase());
    },

    closeCreateTeamDialog() {
      this.createTeamDialog = !this.closeCreateTeamDialog;
    },

    reset() {
      this.nameTeamSearch = "";
      this.countrySearch = "";
      this.tourNameSearch = "";
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
tbody tr:nth-child(odd) {
  background: #dee2e6;
}
</style>
