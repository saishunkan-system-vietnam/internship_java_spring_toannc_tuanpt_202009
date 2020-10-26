<template>
  <v-container class="pt-2">
    <v-row>
      <v-col cols="12" sm="2" style="padding: 5px" class="pr-2">
        <v-card class="mx-auto pt-3">
          <v-list v-for="(tours, t) in tournaments" :key="t">
            <h5 class="pl-3" style="color: #252c35">
              {{ tours.type }}
            </h5>
            <v-list-item-group
              class="pl-3"
              v-for="(tour, t) in tours.tournament"
              :key="t"
            >
              <v-list-item-content>
                <a
                  :href="
                    $router.resolve({
                      path: '/DetailTournament'+tour.type+'/' + tour.idTour,
                    }).href
                  "
                  style="color: black"
                >
                  <v-list-item-title
                    v-text="tour.nameTour"
                    v-b-popover.hover.top="tour.nameTour"
                  ></v-list-item-title
                ></a>
              </v-list-item-content>
            </v-list-item-group>
          </v-list>
        </v-card>
      </v-col>
      <v-col cols="12" sm="8" style="padding: 0px" class="pt-1"
        ><router-view></router-view
      ></v-col>
      <v-col cols="12" sm="2" style="padding: 5px">
        <v-expansion-panels v-model="panel" multiple>
          <v-expansion-panel v-for="(item1, y) in rank" :key="y">
            <v-expansion-panel-header
              style="color: #252c35; font-weight: bold; font-size: 19px"
              >{{ item1.type }}
            </v-expansion-panel-header>
            <v-expansion-panel-content v-for="(team, s) in item1.list" :key="s">
              <a
                :href="
                  $router.resolve({
                    path: '/DetailTeam'+team.team.type+'/' + team.idTeam,
                  }).href
                "
                style="color: black"
              >
                <p v-b-popover.hover.left="team.name">
                  {{ s + 1 }}.
                  {{
                    team.name.length < 10
                      ? team.name
                      : team.name.slice(0, 10) + "..."
                  }}
                </p></a
              >
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
    panel: [0, 1],
  }),
  created() {
    this.recivceData();
    this.recivceRank();
    this.panel = Array.from(Array(10).keys());
  },
  methods: {
    recivceData() {
      let self = this;
      this.$store.dispatch("tournament/getToursByType").then((res) => {
        self.tournaments = res.data;
        // console.log(self.tournaments);
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