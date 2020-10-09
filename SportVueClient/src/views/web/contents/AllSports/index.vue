<template>
  <v-row>
    <v-col class="d-flex pr-0" cols="12" sm="2">
      <v-expansion-panels  multiple>
        <v-expansion-panel v-for="(item, i) in tournaments" :key="i">
          <v-expansion-panel-header>{{item.type}}</v-expansion-panel-header>
          <v-expansion-panel-content>
            {{item.nameTour}}
          </v-expansion-panel-content>
        </v-expansion-panel> </v-expansion-panels
      >.
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
      <v-expansion-panels focusable>
        <v-expansion-panel v-for="(item, i) in 5" :key="i">
          <v-expansion-panel-header>Item</v-expansion-panel-header>
          <v-expansion-panel-content>
            Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do
            eiusmod tempor incididunt ut labore et dolore magna aliqua.
          </v-expansion-panel-content>
        </v-expansion-panel>
      </v-expansion-panels></v-col
    >
  </v-row>
</template>
<script>
export default {
  data: () => ({
    currentItem: "tab-Web",
    tournaments: [],
    more: ["News", "Maps", "Books", "Flights", "Apps"],
    text:
      "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.",
  }),
  created() {
    this.recivceData();
  },
  methods: {
    recivceData() {
      let self = this;
      this.$store.dispatch("tournament/getAll").then((res) => {
        console.log(res.data);
        self.tournaments = res.data;
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