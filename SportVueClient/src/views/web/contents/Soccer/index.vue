<template>
  <v-card>
    <v-tabs v-model="tab" fixed-tabs>
      <v-tabs-slider></v-tabs-slider>
      <v-tab> Tất cả các giải đấu </v-tab>
      <v-tab> Đang diễn ra </v-tab>
      <v-tab> Kết thúc </v-tab>
      <v-tab> Sắp diễn ra </v-tab>
    </v-tabs>
    <v-tabs-items v-model="tab" style="margin-top: 50px">
      <v-tab-item><AllScore></AllScore> </v-tab-item>
      <v-tab-item> <ScoreDoing></ScoreDoing> </v-tab-item>
      <v-tab-item> <ScoreEnd></ScoreEnd></v-tab-item>
      <v-tab-item> <ScoreNot></ScoreNot></v-tab-item>
    </v-tabs-items>
  </v-card>
</template>
<script>
import AllScore from "./AllScore";
import ScoreDoing from "./ScoreDoing";
import ScoreNot from "./ScoreNot";
import ScoreEnd from "./ScoreEnd";

export default {
  components: {
    AllScore,
    ScoreDoing,
    ScoreNot,
    ScoreEnd,
  },
  data() {
    return {
      tab: null,
      tournamet: "",
    };
  },
  created() {
    this.$store.dispatch("tournament/getAll").then((response) => {
      this.tournamet = response.data;
    });
  },
  methods: {
    a() {
      alert("ok");
    },
  },
};
</script>