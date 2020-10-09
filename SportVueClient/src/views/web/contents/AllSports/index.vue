<template>
  <v-row>
    <v-col class="pr-0" cols="12" sm="2">
      <v-expansion-panels max-height="10px" multiple>
        <v-expansion-panel v-for="(item, i) in tournaments" :key="i">
          <v-expansion-panel-header>{{ item.type }}</v-expansion-panel-header>
          <v-expansion-panel-content
            v-for="(tournament, t) in item.tournament"
            :key="t"
          >
            <router-link to="/"></router-link>
            <a
              v-b-popover.hover.left="tournament.nameTour"
              :href="
                $router.resolve({
                  path: '/DetailTournametSoccer/' + item.idTour,
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
    <v-col cols="12" sm="8" class="pl-2">
      <v-card>
        <v-tabs v-model="tab" fixed-tabs>
          <v-tabs-slider></v-tabs-slider>
          <v-tab>Tournaments</v-tab>
          <v-tab>On Game</v-tab>
          <v-tab>Ended</v-tab>
          <v-tab>Upcomming</v-tab>
        </v-tabs>
        <v-tabs-items v-model="tab" style="margin-top: 50px">
          <v-tab-item><AllSoccer></AllSoccer> </v-tab-item>
          <v-tab-item> <SoccerDoing></SoccerDoing> </v-tab-item>
          <v-tab-item> <SoccerEnd></SoccerEnd></v-tab-item>
          <v-tab-item> <SoccerNot></SoccerNot></v-tab-item>
        </v-tabs-items>
      </v-card>
    </v-col>
    <v-col class="d-flex pl-0" cols="12" sm="2">
      <v-expansion-panels max-height="10px" multiple>
        <v-expansion-panel v-for="(item, i) in tournaments" :key="i">
          <v-expansion-panel-header>{{ item.type }}</v-expansion-panel-header>
          <v-expansion-panel-content
            v-for="(tournament, t) in item.tournament"
            :key="t"
          >
            <router-link to="/"></router-link>
            <a
              v-b-popover.hover.left="tournament.nameTour"
              :href="
                $router.resolve({
                  path: '/DetailTournametSoccer/' + item.idTour,
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
      </v-expansion-panels></v-col
    >
  </v-row>
</template>
<script>
export default {
  data: () => ({
    tournaments: [],
    rank: [],
  }),
  created() {
    this.recivceData();
  },
  methods: {
    recivceData() {
      let self = this;
      this.$store.dispatch("tournament/getToursByType").then((res) => {
        console.log(res.data);
        self.tournaments = res.data;
      });
    },
    recivceRank() {
      let self = this;
      this.$store.dispatch("tournament/getRank").then((res) => {
        console.log(res.data);
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