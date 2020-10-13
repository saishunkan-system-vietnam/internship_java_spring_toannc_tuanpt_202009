<template>
  <v-card>
    <v-tabs v-model="tab" class="p-1" fixed-tabs>
      <v-tabs-slider></v-tabs-slider>
      <v-tab>TOURNAMENTS</v-tab>
      <v-tab>ON GAME</v-tab>
      <v-tab>FINISHED</v-tab>
      <v-tab>UPCOMMING</v-tab>
    </v-tabs>
    <v-tabs-items v-model="tab" style="margin-top: 50px">
        <AllTournament></AllTournament>
        <OnGame></OnGame>
        <Finished/>
        <Upcomming/>
       </v-tabs-items>
  </v-card>
</template>
<script>
import OnGame from "./OnGame";
import Finished from "./Finished";
import AllTournament from "./AllTouranment";
import Upcomming from "./Upcomming";
export default {
  components: {
    Upcomming,
    AllTournament,
    OnGame,
    Finished,
  },
  data() {
    return {
      tab: null,
      tournaments: "",
    };
  },
};
</script>
