<template>
  <v-container class="pt-2">
    <v-row>
      <v-col cols="12" sm="2" style="padding: 5px" class="pr-2">
        <div style="margin-bottom: 15px !important" class="pl-15">
        <v-icon large color="red darken-2">home_work</v-icon>
      </div>
      <v-expansion-panels multiple>
        <v-expansion-panel v-for="(item, i) in tournaments" :key="i">
          <v-expansion-panel-header disable-icon-rotate style="color: red">{{
            item.type
          }}</v-expansion-panel-header>
          <v-expansion-panel-content
            v-for="(tournament, t) in item.tournament"
            :key="t"
          >
            <router-link to="/"></router-link>
            <a
              v-b-popover.hover.left="tournament.nameTour"
              :href="
                $router.resolve({
                  path: '/DetailTournametSports/' + tournament.idTour,
                }).href
              "
              style="color: black; margin-left: -30px"
            >
              {{
                tournament.nameTour.length < 10
                  ? tournament.nameTour
                  : tournament.nameTour.slice(0, 10) + "..."
              }}</a
            >
          </v-expansion-panel-content>
        </v-expansion-panel>
      </v-expansion-panels>
      </v-col>
      <v-col cols="12" sm="8" style="padding: 0px" class="pt-1"
        ><router-view></router-view
      ></v-col>
      <v-col cols="12" sm="2" style="padding: 5px">
       <div style="margin-bottom: 15px !important" class="pl-15">
        <v-icon large color="green darken-2">military_tech</v-icon>
      </div>
      <v-expansion-panels multiple>
        <v-expansion-panel v-for="(item1, y) in rank" :key="y">
          <v-expansion-panel-header style="color: green">{{
            item1.type
          }}</v-expansion-panel-header>
          <v-expansion-panel-content v-for="(team, s) in item1.list" :key="s">
            <p v-b-popover.hover.left="team.name">
              {{ s + 1 }} .
              {{
                team.name.length < 10
                  ? team.name
                  : team.name.slice(0, 10) + "..."
              }}
            </p>
          </v-expansion-panel-content>
        </v-expansion-panel>
      </v-expansion-panels>
      </v-col>
    </v-row>
  </v-container>
</template>
<script>

import Sports from "./Sports";
import OnGame from "./OnGame";

export default {
  components: {
    Sports,
    OnGame,

  },
  data: () => ({
    tab: null,
    tournaments: [],
    rank: [],
  }),
  created() {
    this.recivceData();
    this.recivceRank();
  },
  methods: {
    recivceData() {
      let self = this;
      this.$store.dispatch("tournament/getToursByType").then((res) => {
        self.tournaments = res.data;
      });
    },
    recivceRank() {
      let self = this;
      this.$store.dispatch("tournament/rankAll").then((res) => {
        self.rank = res.data;
      });
    },
    addItem(item) {
      const removed = this.items.splice(0, 1);
      this.items.push(...this.more.splice(this.more.indexOf(item), 1));
      this.more.push(...removed);
      this.$nextTick(() => {
        this.currentItem = "tab-" + item;
      });
    },
  },
};
</script>