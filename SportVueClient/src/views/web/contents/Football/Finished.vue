<template>
  <div>
    <div v-if="tournaments == ''" class="text-center">
      <div class="d-flex flex-column justify-space-between align-center">
        <v-img
          max-height="350"
          max-width="350"
          src="@/assets/soccer.png"
        ></v-img>
        No match is finished
      </div>
    </div>
    <v-expansion-panels v-model="panel" multiple>
      <v-expansion-panel v-for="(tournament, i) in tournaments" :key="i">
        <template v-if="i < number">
          <v-expansion-panel-header style="color: #6b4b4b"
            ><h5 @click="detailTournament(tournament.idTour)">{{ tournament.nameTour }}</h5></v-expansion-panel-header
          >
          <v-expansion-panel-content>
            <v-simple-table>
              <tbody>
                <tr style="cursor:pointer"
                  v-for="(item, index) in tournament.schedule"
                  :key="index"
                  v-b-popover.hover.top="'Click to see details'"
                  @click="detail(item)"
                >
                  <template v-if="index < 6">
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
                    <td>{{ item.timeStart }}</td>
                    <td>
                      <v-row>
                        <v-col>{{ item.team[0].nameTeam }}</v-col>
                        <v-col
                          >{{
                            item.status == 2 && item.video != null
                              ? item.scoreTeam1
                              : "?"
                          }}-
                          {{
                            item.status == 2 && item.video != null
                              ? item.scoreTeam2
                              : "?"
                          }}</v-col
                        >
                        <v-col>{{ item.team[1].nameTeam }}</v-col>
                      </v-row>
                    </td>
                  </template>
                </tr>
              </tbody>
            </v-simple-table>

            <div class="text-center" style="font-size: 12px; margin-top: 18px" v-if="tournament.schedule.length>6">
              <router-link :to="'/DetailTournamentFootball/' + tournament.idTour">
                ----- All Matches -----
              </router-link>
            </div>
          </v-expansion-panel-content>
        </template>
      </v-expansion-panel>
    </v-expansion-panels>
    <div
      class="text-center"
      v-if="tournaments.length > this.number"
      @click="show"
      style="color: blue"
    >
      -- Show More --
    </div>
  </div>
</template>
<script>
export default {
  data() {
    return {
      panel: [0, 1, 2, 3, 4, 5, 6, 7, 8],
      tournaments: "",
      number: 6,
    };
  },
  created() {
    this.$store
      .dispatch("tournament/getByStatus", { status: "2", type: "Football" })
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
    show() {
      this.number = this.tournaments.length;
    },
    detailTournament(id){
      this.$router.push('/DetailTournamentFootball/'+id);
    }
  },
};
</script>