<template>
  <v-expansion-panels v-model="panel" multiple>
    <v-expansion-panel v-for="(item, i) in upComingMatch" :key="i">
      <v-expansion-panel-header
        ><template v-if="item.type === 'Football'"
          ><v-icon style="max-width: 30px" medium color="green darken-2">
            mdi-soccer
          </v-icon></template
        >
        <template v-else-if="item.type === 'BaskestBall'"
          ><v-icon style="max-width: 30px" medium color="orange darken-2">
            sports_basketball
          </v-icon></template
        >
        <template v-else-if="item.type === 'TableTennis'"
          ><v-icon style="max-width: 30px" medium color="blue darken-2">
            sports_tennis
          </v-icon></template
        >
        <h5 class="pt-3">{{ item.type }}</h5>
      </v-expansion-panel-header>

      <v-expansion-panel-content>
        <v-expansion-panels v-model="panel1" multiple>
          <v-expansion-panel
            v-for="(tournament, t) in item.tournament"
            :key="t"
          >
            <v-expansion-panel-header>
              <p style="font-weight: bold">{{ tournament.nameTour }}</p>
            </v-expansion-panel-header>
            <v-expansion-panel-content>
              <v-data-table
                @click:row="handleRowClick"
                :headers="headers"
                :items="tournament.schedule"
                hide-default-footer
                :items-per-page="100"
                class="elevation-1 row-pointer"
              >
                <template v-slot:[`item.status`]="{}">
                  <p style="color: green" class="mb-0">Up Comming</p>
                </template>
                <template v-slot:[`item.score`]="{ item }">
                  <p
                    style="font-weight: bold"
                    class="mb-0 pr-5"
                    v-if="item.scoreTeam1 != 0 && item.scoreTeam2 != 0"
                  >
                    {{ item.scoreTeam1 }} - {{ item.scoreTeam2 }}
                  </p>
                  <p class="mb-0 pr-5" v-else>? - ?</p>
                </template>
              </v-data-table>
            </v-expansion-panel-content>
          </v-expansion-panel>
        </v-expansion-panels>
      </v-expansion-panel-content>
    </v-expansion-panel>
  </v-expansion-panels>
</template>

<script>
import Schedule from "../../../../models/schedule";
export default {
  data() {
    return {
      disabled: false,
      open: false,
      upComingMatch: [],
      panel: [],
      panel1: [],
      headers: [
        { text: "Date", value: "timeStart" },
        { text: "Status", value: "status" },
        { text: "Team 1", value: "nameTeam1" },
        { text: "", value: "score", sortable: false },
        { text: "Team 2", value: "nameTeam2" },
      ],
      desserts: [],
    };
  },
  mounted() {
    this.recivceData();
    this.panel = Array.from(Array(10).keys());
    this.panel1 = Array.from(Array(10).keys());
  },
  methods: {
    recivceData() {
      let self = this;
      this.$store.dispatch("tournament/getToursByType").then((res) => {
        self.upComingMatch = res.data.map((v) => {
          let newTournament = v.tournament
            .filter((i) => {
              return i.status != 2;
            })
            .map((k) => {
              let newSchedule = k.schedule.filter((j) => {
                return j.status == 0;
              });
              k.schedule = newSchedule;
              return k;
            });
          v["tournament"] = newTournament;
          return v;
        });
      });
    },

    handleRowClick(item) {
      var myWindow = window.open(
        "http://localhost:8080/detail/" + item.idSchedule,
        "myWindow",
        "width=600px,height=600"
      );
    },
  },
};
</script>

<style lang="css" scoped>
  .row-pointer >>> tbody tr :hover {
    cursor: pointer;
  }
</style>