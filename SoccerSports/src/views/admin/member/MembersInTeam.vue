<template>
  <v-data-table
    :headers="headers"
    :items="playersInTeam"
    class="elevation-1"
    :options.sync="options"
  >
    <template v-slot:top>
      <v-toolbar flat color="white">
        <v-row class="mt-4">
          <v-col cols="12" sm="3" md="3">
            <v-text-field
              v-model="countrySearch"
              append-icon="mdi-magnify"
              label="Country"
              single-line
              hide-details
            ></v-text-field>
          </v-col>
          <v-col cols="12" sm="3" md="3">
            <v-text-field
              v-model="nameMemberSearch"
              append-icon="mdi-magnify"
              label="Name"
              single-line
              hide-details
            ></v-text-field>
          </v-col>
          <v-col cols="12" sm="3" md="3">
            <v-select v-model="ageSearch" :items="ages" label="Age"></v-select>
          </v-col>
          <v-col cols="12" sm="3" md="3">
            <v-select
              v-model="positionSearch"
              label="Position"
              :items="positionItems"
            ></v-select>
          </v-col>
        </v-row>
      </v-toolbar>
    </template>
    <template v-slot:[`item.avatar`]="{ item }">
      <v-img
        :src="baseUrl + item.avatar"
        alt=""
        style="margin: 5px 0 5px 0"
        class="fixImg"
      />
    </template>

    <template v-slot:[`item.actions`]="{ item }">
      <v-btn
        class="mr-1 mb-1"
        color="primary"
        small
        @click="removeMember(item)"
      >
        Kick
      </v-btn>
      <v-btn color="primary mb-1" small @click="editPlayer(item)">Edit</v-btn>
    </template>
  </v-data-table>
</template>
<script>
import { ENV } from "@/config/env.js";
export default {
  props: {
    playersInTeam: Array,
    removedMember: {
      type: Function,
    },
    isConfirm: {
      type: Function,
    },
  },
  data() {
    return {
      options: {},
      checkUpdate: true,
      backUpPlayes: [],
      positionSearch: "",
      positionItems: [
        "Default",
        "Goalkeepers",
        "Defenders",
        "Midfielders",
        "Forwards",
      ],
      ageSearch: "",
      nameMemberSearch: "",
      countrySearch: "",
      ages: Array.from(Array(46).keys()).map((v) => v + 10),
      headers: [
        {
          text: "Avatar",
          align: "start",
          sortable: false,
          value: "avatar",
        },
        { text: "Name", value: "name", filter: this.nameMemberFilter },
        { text: "Age", value: "currentAge", filter: this.ageFilter },
        { text: "Gender", value: "gender" },
        { text: "Position", value: "position", filter: this.positionFilter },
        { text: "Country", value: "country", filter: this.countryFilter },
        { text: "Actions", value: "actions", sortable: false },
      ],
    };
  },

  // updated() {
  //   if (this.checkUpdate) {
  //     console.log("Run 1")
  //     this.checkUpdate = false;
  //     this.backUpPlayers = this.playersInTeam;
  //     console.log(this.backUpPlayers)
  //   }
  // },

  computed: {
    baseUrl() {
      return ENV.BASE_IMAGE;
    },
  },

  watch: {
    playersInTeam: {
      deep: true,
      handler(newValue, oldValue) {
        // console.log("Run herer");
        // let copiedArray = oldValue;
        // console.log(newValue);
        // console.log(oldValue);
        if (oldValue.length > 0 && newValue != oldValue)
          this.checkUpdate = false;
      },
    },

    watch: {
      nameTeamSearch() {
        this.options.page = 1;
      },
    },
  },

  methods: {
    countryFilter(value) {
      // If this filter has no value we just skip the entire filter.
      if (!this.countrySearch) {
        return true;
      }

      return value.toLowerCase().includes(this.countrySearch.toLowerCase());
    },

    nameMemberFilter(value) {
      // If this filter has no value we just skip the entire filter.
      if (!this.nameMemberSearch) {
        return true;
      }

      return value.toLowerCase().includes(this.nameMemberSearch.toLowerCase());
    },

    positionFilter(value) {
      // If this filter has no value we just skip the entire filter.
      if (!this.positionSearch) {
        return true;
      }
      if (this.positionSearch == "Default") return true;
      return value === this.positionSearch;
    },

    ageFilter(value) {
      if (!this.ageSearch) {
        return true;
      }
      return value == this.ageSearch;
    },

    removeMember(member) {
      let obj = Object.assign({}, member);
      let newArray = this.playersInTeam.filter(
        (element) => element.id != member.id
      );
      this.removedMember(obj, newArray);
      // console.log(this.playersInTeam);
    },

    editPlayer(player) {
      if (this.checkUpdate) {
        this.$router.push({
          path: `/admin/member/${player.id}`,
        });
      } else {
        this.isConfirm(player.id);
      }
    },

    resetInTeam() {
      this.nameMemberSearch = "";
      this.ageSearch = "";
      this.countrySearch = "";
      this.positionSearch = "Default";
      this.countryFilter();
      this.nameMemberFilter();
      this.positionFilter();
      this.ageFilter();
    },
  },
};
</script>
<style scoped>
.fixImg {
  height: 100px;
  width: 100px;
}
</style>