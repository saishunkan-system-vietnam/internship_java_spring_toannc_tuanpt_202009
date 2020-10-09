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
            <v-card flat>
              <v-card-text>
                <h2>{{ item }}</h2>
                {{ text }}
              </v-card-text>
            </v-card>
          </v-tab-item>
        </v-tabs-items>
      </v-card></v-col
    >
    <v-col class="d-flex" cols="12" sm="2">Right</v-col>
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