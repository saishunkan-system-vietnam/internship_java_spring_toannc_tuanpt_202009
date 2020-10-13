<template>
  <v-tab-item>
    <v-expansion-panels v-model="panel" multiple>
      <v-expansion-panel v-for="(tournament, i) in tournaments" :key="i" >
        <v-expansion-panel-header style="color:#6b4b4b"
          ><h5>{{ tournament.nameTour }}</h5></v-expansion-panel-header
        >
        <v-expansion-panel-content>
          <v-simple-table>
            <tbody>
              <tr
                v-for="(item, index) in tournament.schedule"
                :key="index"
                v-b-popover.hover.top="'Click to see details'"
                @click="detail(item)"
              >
              <template v-if="index<5">
                <td
                  width="180px"
                  :style="
                    item.status == 0
                      ? 'color:green'
                      : item.status == 1
                      ? 'color:blue'
                      : 'color:#68688e'
                  "
                >
                  {{
                    item.status == 0
                      ? "UPCOMING"
                      : item.status == 1
                      ? "ON GAME"
                      : "FINISHED"
                  }}
                </td>
                <td>
                
                  <v-row>
                    <v-col>{{ item.team[0].nameTeam }}</v-col>
                    <v-col 
                      >{{ item.status == 2 &&item.video!=null? item.scoreTeam1 : "?" }}-
                      {{ item.status == 2 &&item.video!=null ? item.scoreTeam2 : "?" }}</v-col
                    >
                    <v-col>{{ item.team[1].nameTeam }}</v-col>
                  </v-row>
                </td>
              </template>
              </tr>
            </tbody>
          </v-simple-table>

          <div class="text-center" style="font-size: 12px;margin-top: 18px;">
              <router-link :to="'/DetailTournamentSoccer/'+tournament.idTour">
             ----- All Matches -----
              </router-link>
          </div>
        </v-expansion-panel-content>
      </v-expansion-panel>
    </v-expansion-panels></v-tab-item
  >
</template>
<script>
export default {
  data() {
    return {
      panel: [0, 1, 2, 3, 4, 5],
      tournaments: "",
    };
  },
  created() {
    this.$store
      .dispatch("tournament/getByType", "Football")
      .then((response) => {
        this.tournaments = response.data;
      });
  },
  methods: {
    detail(data) {
      var myWindow = window.open(
        "http://localhost:8080/detail/" + data.idSchedule,
        "myWindow",
        "width=600px,height=600"
      );
    },
  },
};
</script>