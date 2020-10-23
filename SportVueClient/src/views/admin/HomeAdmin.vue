<template>
  <div>
    <v-row>
      <v-col @click="tournament1"
        ><div class="border1" style="background-color: green; cursor: pointer">
          <h3 class="text-center">{{ this.tournament.length }} TOURNAMENT</h3>
        </div></v-col
      >
      <v-col @click="schedule1"
        ><div class="border1" style="background-color: blue; cursor: pointer">
          <h3 class="text-center">{{ this.schedule.length }} SCHEDULE</h3>
        </div></v-col
      >
      <v-col @click="team1"
        ><div class="border1" style="background-color: red; cursor: pointer">
          <h3 class="text-center">{{ this.team.length }} TEAM</h3>
        </div></v-col
      >
    </v-row>
    <h2>Notification</h2>
    <!-- <div v-for="(item,i) in schedule" :key="i">
        <div v-if="item.video==null &&item.status==2"> -->
    <v-simple-table>
      <template v-slot:default>
        <thead>
          <tr>
            <th class="text-left">Name</th>
            <th class="text-left">Time</th>
            <th class="text-left">Team</th>
            <th class="text-left"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="(item, i) in schedule" :key="i">
            <template v-if="item.video == null && item.status == 2">
              <td>{{ item.title }}</td>
              <td>{{ item.timeStart }} ->{{ item.timeEnd }}</td>
              <td>
                {{ item.team[0].nameTeam }}-<b>vs</b>-{{
                  item.team[1].nameTeam
                }}
              </td>
              <td>
                <a
                  :href="
                    $router.resolve({
                      path: '/DetailSchedule/' + item.idSchedule,
                    }).href
                  "
                  >Update Now
                </a>
              </td>
            </template>
          </tr>
        </tbody>
      </template>
    </v-simple-table>
  </div>
</template>
<script>
export default {
  data() {
    return {
      tournament: "",
      schedule: "",
      team: "",
    };
  },
  created() {
    this.$store.dispatch("tournament/getAll").then((response) => {
      this.tournament = response.data;
    });
    this.$store.dispatch("team/getAll").then((response) => {
      this.team = response.data;
    });
    this.$store.dispatch("schedule/getAll").then((response) => {
      this.schedule = response.data;
      // console.log(this.schedule);
    });
  },
  methods: {
    tournament1() {
      this.$router.push("/LayoutTournament");
    },
    schedule1() {
      this.$router.push("/LayoutSchedule");
    },
    team1() {
      this.$router.push("/LayoutTeam");
    },
  },
};
</script>
<style>
.border1 {
  border-radius: 30px;
  background-color: green;
  color: white;
  width: 300px;
  height: 100px;
  padding: 20px;
}
</style>
