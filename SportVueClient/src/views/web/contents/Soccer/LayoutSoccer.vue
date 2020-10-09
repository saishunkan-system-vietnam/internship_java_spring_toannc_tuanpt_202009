<template>
<v-card>
    <v-tabs v-model="tab" fixed-tabs>
      <v-tabs-slider></v-tabs-slider>
      <v-tab>Các giải đấu</v-tab>
      <v-tab>Đang diễn ra</v-tab>
      <v-tab>Kết thúc</v-tab>
      <v-tab>Sắp diễn ra</v-tab>
    </v-tabs>
    <v-tabs-items v-model="tab" style="margin-top: 50px">
      <v-tab-item><AllSoccer></AllSoccer> </v-tab-item>
      <v-tab-item> <SoccerDoing></SoccerDoing> </v-tab-item>
      <v-tab-item> <SoccerEnd></SoccerEnd></v-tab-item>
      <v-tab-item> <SoccerNot></SoccerNot></v-tab-item>
    </v-tabs-items>
  </v-card>
    
  
</template>
<script>
import AllSoccer from "./AllSoccer";
import SoccerDoing from "./SoccerDoing";
import SoccerNot from "./SoccerNot";
import SoccerEnd from "./SoccerEnd";

export default {
  components: {
    AllSoccer,
    SoccerDoing,
    SoccerNot,
    SoccerEnd,
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
   
  },
};
</script>