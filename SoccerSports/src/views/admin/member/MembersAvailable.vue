<template>
  <v-data-table
    :headers="headers"
    :items="playersAvailable"
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
      <v-btn class="mr-1 mb-1" color="primary" small @click="addMember(item)">
        Add
      </v-btn>

      <v-btn color="primary mb-1" small @click="editPlayer(item)">Edit</v-btn>
    </template>
  </v-data-table>
</template>
<script>
import { ENV } from "@/config/env.js";
export default {
  props: {
    playersAvailable: Array,
    backUpPlayers: Array,
    addedMember: {
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
      positionSearch: "",
      positionItems: [
        "Default",
        "Goalkeepers",
        "Defenders",
        "Midfielders",
        "Forwards",
      ],
      countrySearch: "",
      nameMemberSearch: "",
      ageSearch: "",
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

  computed: {
    baseUrl() {
      return ENV.BASE_IMAGE;
    },
  },

  watch: {
    playersAvailable: {
      deep: true,
      handler(newValue, oldValue) {
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
      if (!this.countrySearch) {
        return true;
      }
      return value.toLowerCase().includes(this.countrySearch.toLowerCase());
    },

    nameMemberFilter(value) {
      if (!this.nameMemberSearch) {
        return true;
      }
      return value.toLowerCase().includes(this.nameMemberSearch.toLowerCase());
    },

    positionFilter(value) {
      if (!this.positionSearch) {
        return true;
      }
      if (this.positionSearch === "Default") {
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

    addMember(member) {
      this.checkAdd = false;
      let indexRemove = 0;
      let obj = Object.assign({}, member);
      let newArray = [...this.playersAvailable];
      newArray.forEach((element, index) => {
        if (element.id === member.id) {
          indexRemove = index;
        }
      });
      newArray.splice(indexRemove, 1);
      this.addedMember(obj, newArray);
      // this.playersAvailable = newArray
    },

    editPlayer(player) {
      console.log(this.checkUpdate);
      if (this.checkUpdate) {
        this.$router.push({
          path: `/admin/member/${player.id}`,
        });
      } else {
        this.isConfirm(player.id);
      }
    },

    resetAvaiable() {
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