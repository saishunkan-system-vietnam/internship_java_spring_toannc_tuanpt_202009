<template>
    <div>
        <v-card>
        <v-tabs fixed-tabs v-model="tab">
          <v-tabs-slider></v-tabs-slider>
          <v-tab>Tournaments</v-tab>
          <v-tab>On Game</v-tab>
          <v-tab>Finished</v-tab>
          <v-tab>Upcomming</v-tab>
        </v-tabs>
        <v-tabs-items v-model="tab" style="margin-top: 50px">
          <v-tab-item><Sports></Sports> </v-tab-item>
          <v-tab-item><OnGame></OnGame> </v-tab-item>
          <v-tab-item><Finished></Finished></v-tab-item>
          <v-tab-item><Upcomming></Upcomming></v-tab-item>
        </v-tabs-items>
      </v-card>
    </div>
</template>
<script>
import Sports from "./Sports";
import OnGame from "./OnGame";
import Finished from './Finished'
import Upcomming from './Upcomming'

export default {
  components: {
    Sports,
    OnGame,
    Finished,
    Upcomming
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