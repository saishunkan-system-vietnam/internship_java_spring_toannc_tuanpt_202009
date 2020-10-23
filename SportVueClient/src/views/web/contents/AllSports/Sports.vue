<template>
  <v-expansion-panels v-model="panel" multiple>
    <v-expansion-panel v-for="(item, i) in tournaments" :key="i">
      <v-expansion-panel-header v-if="item.schedule!=''"
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
            <v-expansion-panel-content class="remove-padding">
              <v-data-table
                @click:row="handleRowClick"
                :headers="headers"
                :items="tournament.schedule"
                hide-default-footer
                :items-per-page="100"
                class="elevation-1 row-pointer"
                :custom-sort="customSort"
              >
                <template v-slot:[`item.status`]="{ item }">
                  <p style="color: red" class="mb-0" v-if="item.status === 2">
                    FINISHED
                  </p>
                  <p
                    style="color: blue"
                    class="mb-0"
                    v-else-if="item.status === 1"
                  >
                    On Game
                  </p>
                  <p style="color: green" class="mb-0" v-else>Up Comming</p>
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
export default {
  data() {
    return {
      open: false,
      tournaments: [],
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
    // console.log(this.tournaments);
    this.recivceData();
    this.panel = Array.from(Array(10).keys());
    this.panel1 = Array.from(Array(10).keys());
  },
  methods: {
    recivceData() {
      let self = this;
      this.$store.dispatch("tournament/getToursByType").then((res) => {
        self.tournaments = res.data;
      });
    },
    detail(item) {
      this.detailScore = item;
      this.open = true;
      var myWindow = window.open(
        "http://localhost:8080/detail/" + item.idSchedule,
        "myWindow",
        "width=600px,height=600"
      );
    },
    handleRowClick(item) {
      var myWindow = window.open(
        "http://localhost:8080/detail/" + item.idSchedule,
        "myWindow",
        "width=600px,height=600"
      );
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
</style>