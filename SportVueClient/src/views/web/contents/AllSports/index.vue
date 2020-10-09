<template>
  <v-row>

    <v-col class="pr-0" cols="12" sm="2">
      <div style="margin-bottom: 15px !important" class="pl-15"> <v-icon large color="red darken-2">home_work</v-icon></div>
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
        <v-tabs fixed-tabs>
          <v-tabs-slider></v-tabs-slider>
          <v-tab>Tournaments</v-tab>
          <v-tab>On Game</v-tab>
          <v-tab>Ended</v-tab>
          <v-tab>Upcomming</v-tab>
        </v-tabs>
        <v-tabs-items style="margin-top: 50px">
          <v-tab-item><AllSoccer></AllSoccer> </v-tab-item>
          <v-tab-item> <SoccerDoing></SoccerDoing> </v-tab-item>
          <v-tab-item> <SoccerEnd></SoccerEnd></v-tab-item>
          <v-tab-item> <SoccerNot></SoccerNot></v-tab-item>
        </v-tabs-items>
      </v-card>
    </v-col>
    <v-col class="pl-0" cols="12" sm="2">
      <div style="margin-bottom: 15px !important" class="pl-15"> <v-icon large color="green darken-2">military_tech</v-icon></div>
      <v-expansion-panels multiple>
        <v-expansion-panel v-for="(item1, y) in rank" :key="y">
          <v-expansion-panel-header style="color: green">{{ item1.type }}</v-expansion-panel-header>
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
</template>
<script>
export default {
  data: () => ({
    currentItem: "tab-Web",
    items: ["Web", "Shopping", "Videos", "Images"],
    more: ["News", "Maps", "Books", "Flights", "Apps"],
    text:
      "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.",
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