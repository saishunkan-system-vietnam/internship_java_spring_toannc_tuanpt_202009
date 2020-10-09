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
      <v-tab-item><AllTableTennis></AllTableTennis> </v-tab-item>
      <v-tab-item> <TableTennisDoing></TableTennisDoing> </v-tab-item>
      <v-tab-item> <TableTennisEnd></TableTennisEnd></v-tab-item>
      <v-tab-item> <TableTennisNot></TableTennisNot></v-tab-item>
    </v-tabs-items>
  </v-card>
    
  
</template>
<script>
import AllTableTennis from "./AllTableTennis";
import TableTennisDoing from "./TableTennisDoing";
import TableTennisEnd from "./TableTennisEnd";
import TableTennisNot from "./TableTennisNot";

export default {
  components: {
    AllTableTennis,
    TableTennisDoing,
    TableTennisEnd,
    TableTennisNot,
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