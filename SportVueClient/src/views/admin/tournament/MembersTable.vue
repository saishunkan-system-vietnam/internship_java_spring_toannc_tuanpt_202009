<template>
  <div>
    <v-data-table
      :headers="headers"
      :items="desserts"
      :items-per-page="10"
      class="elevation-1"
      width="1000px"
    ></v-data-table>
  </div>
</template>
<script>
import axios from "axios";

export default {
  props: {
    teamData: {
      type: Object,
    },
  },
  data() {
    return {
      headers: [
        {
          text: "Avatar",
          align: "start",
          sortable: false,
          value: "avatar",
        },
        { text: "Name", value: "name" },
        { text: "Email", value: "email" },
        { text: "Phone", value: "phone" },
        { text: "Age", value: "age" },
        { text: "Gender", value: "gender" },
        { text: "Current Team", value: "idTeam" },
      ],

      desserts: [],
    };
  },

  watch: {
    teamData: {
      immediate: true,
      deep: true,
      handler(newValue, oldValue) {
        // let teamDataOld = JSON.stringify(oldValue);
        // let teamDataNew = JSON.stringify(newValue);
        // if(teamDataNew != teamDataOld){
            this.teamData = newValue;
            this.loadListMember();
        // }
      },
    },
  },
  created() {
    this.loadListMember();
  },
  methods: {
    loadListMember() {
      let self = this;
      axios
        .get(`http://localhost:8090/api/v1/team/teamTourHistory`, {
          params: {
            idTour: this.teamData.idTour,
            idTeam: this.teamData.idTeam,
          },
        })
        .then(function (response) {
          self.desserts = response.data.profile;
        })
        .catch(function (error) {
          // handle error
          console.log(error);
        });
    },
  },
};
</script>